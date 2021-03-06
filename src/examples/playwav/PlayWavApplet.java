package examples.playwav;

import jass.render.*;
import jass.engine.*;
import jass.generators.*;
import java.net.*;

/**
   Play some looping wav file in chorus.
   @author Kees van den Doel (kvdoel@cs.ubc.ca)
*/
public class PlayWavApplet extends AppletController {
    SourcePlayer player;
    LoopBuffer[] loopbuffer;
    Mixer mixer;
    int nvoices = 5;
    boolean isOn = false;
    float speed = 1;
    String wavfile =  "../data/hello.wav";
    
    public void setNSliders() {
        nsliders = nvoices*2;
    }
   
    public void setNButtons() {
        nbuttons = 1;
    }
    
    public void init() {
        super.init();
        wavfile = getParameter("wavfile");
    }
    
    public void start() {
        float srate = 44100.f;
        int bufferSize = 128*4;
        int bufferSizeJavaSound = 8*1024;
        loopbuffer = new LoopBuffer[nvoices];
        URL codebase = getCodeBase();
        URL wavurl = null;
        try {
            wavurl = new URL(codebase,wavfile);
        } catch(MalformedURLException e) {
            System.out.println(e+" Malformed URL: " +codebase+" "+ wavfile);
        }
        loopbuffer[0] = new LoopBuffer(srate,bufferSize,wavurl);
        float[] wavArray = loopbuffer[0].getLoopBuffer();
        for(int i=1;i < nvoices;i++) {
            loopbuffer[i] = new LoopBuffer(srate,bufferSize,wavArray);
        }
        mixer = new Mixer(bufferSize,nvoices);

        for(int i=0;i < nvoices;i++) {
            mixer.setGain(i,1);
        }
        player = new SourcePlayer(bufferSize,bufferSizeJavaSound,srate);
        try {
            player.addSource(mixer);
            for(int i=0;i < nvoices;i++) {
                mixer.addSource(loopbuffer[i]);
            }
        } catch(SinkIsFullException e) {
        }
        String[] names = new String[2*nvoices];
        double[] val = new double[2*nvoices];
        double[] min = new double[2*nvoices];
        double[] max = new double[2*nvoices];
        for(int i=0;i < nvoices;i++) {
            names[i] = "Speed" + i+" ";
            val[i] = (float)(.95 + i/20.);
            min[i] = .2f;
            max[i] = 5f;
        }
        for(int i=nvoices;i < 2*nvoices;i++) {
            int k = i-nvoices;
            names[i] = "Volume" + k +" ";
            val[i] = 1f;
            min[i] = 0;
            max[i] = 1f;
        }
        for(int i=0;i < nvoices;i++) {
            loopbuffer[i].setSpeed((float)val[i]);
        }
        setValues(val,min,max,names);
        jButton[0].setText ("Stop");
        
        player.start();
        isOn = true;
    }

    protected void jButtonMousePressed (int k, java.awt.event.MouseEvent evt) {
        switch(k) {
        case 0:
            if(isOn) {
                jButton[0].setText ("Start");
                isOn = false;                
                for(int i=0;i < nvoices;i++) {
                    loopbuffer[i].setVolume(0);
                }
            } else {
                jButton[0].setText ("Stop");
                isOn = true;
                player.resetAGC();
                for(int i=0;i < nvoices;i++) {
                    loopbuffer[i].setVolume(1);
                }
            }
            break;
        }

    }
    
    protected void onSlider(int k) {
        if(k < nvoices) {
            loopbuffer[k].setSpeed((float)this.val[k]);
        } else {
            mixer.setGain(k - nvoices,(float)this.val[k]);
        }
    }
    
}
