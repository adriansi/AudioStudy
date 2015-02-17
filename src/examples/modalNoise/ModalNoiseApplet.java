package examples.modalNoise;

import jass.render.*;
import jass.engine.*;
import jass.generators.*;

/**
   Filter white noise through a modal resonbank.
   @author Kees van den Doel (kvdoel@cs.ubc.ca)
*/
public class ModalNoiseApplet extends AppletController {

    RandOut rout;
    SourcePlayer player;
    ModalObjectWithOneContact mob;
    ModalModel mm;
    
    int nmodes = 3;
    float srate = 44100.f;
    double fmin = 50;
    double fmax = srate/4;
    double dmin = 1;
    double dmax = srate/10;
    double amin = 0;
    double amax = 1;

    String[] names = new String[nmodes*3];
    double[] val =   new double[nmodes*3];
    double[] min =  new double[nmodes*3];
    double[] max = new double[nmodes*3];
    
    public void setNSliders() {
        nsliders = nmodes*3;
    }

    public void setNButtons() {
        nbuttons = 2;
    }
    
    public void initRandom() {
        for(int i=0;i<nmodes;i++) {
            mm.f[i] = (float)(fmin + (fmax-fmin)*Math.random());
            mm.d[i] = (float)(dmin + (dmax-dmin)*Math.random());
            mm.a[0][i] = (float)(amin + (amax-amin)*Math.random());
        }
    }
    
    public void start() {

        int bufferSize = 128*4;
        int bufferSizeJavaSound = 8*1024;
        rout = new RandOut(bufferSize);
        player = new SourcePlayer(bufferSize,bufferSizeJavaSound,srate);
        mm = new ModalModel(nmodes,1);
        mm.f = new float[nmodes];
        mm.d = new float[nmodes];
        mm.a = new float[1][nmodes];
        initRandom();
        mob =  new ModalObjectWithOneContact(mm,srate,bufferSize);
        try {
            player.addSource(mob);
            mob.addSource(rout);
        } catch(SinkIsFullException e) {
        }

        for(int i=0;i<nmodes;i++) {
            names[i] = "freq" + i + " ";
            names[i+nmodes] = "damping" + i + " ";
            names[i+2*nmodes] = "gain" + i + " ";
            val[i] = mm.f[i];
            val[i+nmodes] = mm.d[i];
            val[i+2*nmodes] = mm.a[0][i];
            min[i] = fmin;
            min[i+nmodes] = dmin;
            min[i+2*nmodes] = amin;
            max[i] = fmax;
            max[i+nmodes] = dmax;
            max[i+2*nmodes] = amax;
        }
        setValues(val,min,max,names);
        jButton[0].setText ("Randomize");
        jButton[1].setText ("Reset AGC");
        
        player.start();
    }

    protected void jButtonMousePressed (int k,java.awt.event.MouseEvent evt) {
        switch(k) {
        case 0:
            initRandom();
            for(int i=0;i<nmodes;i++) {
                val[i] = mm.f[i];
                val[i+nmodes] = mm.d[i];
                val[i+2*nmodes] = mm.a[0][i];
            }
            setValues(val,min,max,names);
            mob.computeFilter();
            player.resetAGC();
            break;
        case 1:
            player.resetAGC();
            break;
        }
    }
    
    protected void onSlider(int k) {
        if(k<nmodes) {
            mm.f[k] = (float)(super.val[k]);
        } else if(k<nmodes*2) {
            mm.d[k-nmodes] = (float)(super.val[k]);
        } else if(k<nmodes*3) {
            mm.a[0][k-2*nmodes] = (float)(super.val[k]);
        }
        mob.computeFilter();
    }
}
