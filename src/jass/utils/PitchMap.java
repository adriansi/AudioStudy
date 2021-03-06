package jass.utils;

/** Map hue 0-1 to perceptually more uniform variable pitch(h)
    @author Kees van den Doel (kvdoel@cs.ubc.ca)
*/

public class PitchMap {
    static final int N = 1000;
    static final double dt = 0.01;
    // data at 0:0.001:1
    // N+1 array elements
    static private final double[] hue2pitchData = {
        0.000000,
        0.002622,
        0.005163,
        0.007624,
        0.010007,
        0.012314,
        0.014544,
        0.016701,
        0.018785,
        0.020797,
        0.022738,
        0.024611,
        0.026416,
        0.028155,
        0.029828,
        0.031438,
        0.032985,
        0.034472,
        0.035898,
        0.037266,
        0.038577,
        0.039832,
        0.041033,
        0.042180,
        0.043275,
        0.044320,
        0.045315,
        0.046263,
        0.047164,
        0.048019,
        0.048831,
        0.049600,
        0.050327,
        0.051014,
        0.051663,
        0.052274,
        0.052849,
        0.053389,
        0.053896,
        0.054370,
        0.054814,
        0.055228,
        0.055613,
        0.055972,
        0.056305,
        0.056614,
        0.056900,
        0.057164,
        0.057408,
        0.057633,
        0.057840,
        0.058031,
        0.058206,
        0.058368,
        0.058517,
        0.058655,
        0.058784,
        0.058903,
        0.059016,
        0.059122,
        0.059224,
        0.059323,
        0.059420,
        0.059516,
        0.059613,
        0.059711,
        0.059813,
        0.059920,
        0.060032,
        0.060152,
        0.060281,
        0.060419,
        0.060568,
        0.060730,
        0.060906,
        0.061096,
        0.061304,
        0.061529,
        0.061773,
        0.062037,
        0.062323,
        0.062632,
        0.062966,
        0.063325,
        0.063711,
        0.064125,
        0.064569,
        0.065044,
        0.065551,
        0.066091,
        0.066667,
        0.067278,
        0.067927,
        0.068615,
        0.069343,
        0.070112,
        0.070924,
        0.071780,
        0.072681,
        0.073629,
        0.074625,
        0.075670,
        0.076766,
        0.077914,
        0.079115,
        0.080371,
        0.081682,
        0.083051,
        0.084478,
        0.085965,
        0.087513,
        0.089123,
        0.090797,
        0.092537,
        0.094342,
        0.096216,
        0.098158,
        0.100171,
        0.102255,
        0.104413,
        0.106644,
        0.108951,
        0.111335,
        0.113797,
        0.116339,
        0.118962,
        0.121667,
        0.124455,
        0.127328,
        0.130287,
        0.133333,
        0.136467,
        0.139679,
        0.142961,
        0.146303,
        0.149694,
        0.153126,
        0.156589,
        0.160073,
        0.163568,
        0.167064,
        0.170553,
        0.174025,
        0.177469,
        0.180876,
        0.184236,
        0.187540,
        0.190778,
        0.193941,
        0.197018,
        0.200000,
        0.202880,
        0.205657,
        0.208335,
        0.210915,
        0.213401,
        0.215794,
        0.218097,
        0.220312,
        0.222443,
        0.224491,
        0.226458,
        0.228348,
        0.230163,
        0.231904,
        0.233575,
        0.235179,
        0.236716,
        0.238191,
        0.239605,
        0.240960,
        0.242260,
        0.243506,
        0.244702,
        0.245849,
        0.246950,
        0.248008,
        0.249024,
        0.250002,
        0.250943,
        0.251851,
        0.252727,
        0.253575,
        0.254396,
        0.255193,
        0.255968,
        0.256724,
        0.257463,
        0.258189,
        0.258902,
        0.259606,
        0.260303,
        0.260995,
        0.261686,
        0.262377,
        0.263070,
        0.263769,
        0.264476,
        0.265193,
        0.265922,
        0.266667,
        0.267428,
        0.268207,
        0.269001,
        0.269812,
        0.270639,
        0.271480,
        0.272336,
        0.273206,
        0.274089,
        0.274985,
        0.275893,
        0.276814,
        0.277746,
        0.278688,
        0.279642,
        0.280605,
        0.281577,
        0.282559,
        0.283549,
        0.284546,
        0.285552,
        0.286564,
        0.287582,
        0.288606,
        0.289636,
        0.290670,
        0.291709,
        0.292752,
        0.293798,
        0.294847,
        0.295898,
        0.296951,
        0.298005,
        0.299060,
        0.300116,
        0.301171,
        0.302226,
        0.303279,
        0.304331,
        0.305380,
        0.306427,
        0.307471,
        0.308510,
        0.309546,
        0.310577,
        0.311603,
        0.312623,
        0.313636,
        0.314643,
        0.315643,
        0.316635,
        0.317618,
        0.318593,
        0.319559,
        0.320514,
        0.321460,
        0.322394,
        0.323318,
        0.324229,
        0.325129,
        0.326015,
        0.326888,
        0.327748,
        0.328593,
        0.329423,
        0.330238,
        0.331037,
        0.331819,
        0.332585,
        0.333333,
        0.334064,
        0.334777,
        0.335473,
        0.336151,
        0.336813,
        0.337457,
        0.338086,
        0.338698,
        0.339294,
        0.339874,
        0.340439,
        0.340988,
        0.341522,
        0.342042,
        0.342546,
        0.343037,
        0.343513,
        0.343975,
        0.344423,
        0.344858,
        0.345279,
        0.345688,
        0.346084,
        0.346467,
        0.346838,
        0.347196,
        0.347543,
        0.347878,
        0.348202,
        0.348514,
        0.348816,
        0.349106,
        0.349386,
        0.349656,
        0.349916,
        0.350166,
        0.350406,
        0.350637,
        0.350859,
        0.351072,
        0.351276,
        0.351472,
        0.351660,
        0.351839,
        0.352011,
        0.352175,
        0.352332,
        0.352482,
        0.352625,
        0.352761,
        0.352891,
        0.353015,
        0.353133,
        0.353245,
        0.353351,
        0.353453,
        0.353549,
        0.353641,
        0.353728,
        0.353810,
        0.353889,
        0.353964,
        0.354035,
        0.354103,
        0.354167,
        0.354229,
        0.354287,
        0.354344,
        0.354398,
        0.354450,
        0.354500,
        0.354549,
        0.354596,
        0.354642,
        0.354687,
        0.354732,
        0.354776,
        0.354820,
        0.354864,
        0.354908,
        0.354953,
        0.354998,
        0.355045,
        0.355092,
        0.355141,
        0.355192,
        0.355244,
        0.355299,
        0.355356,
        0.355415,
        0.355477,
        0.355542,
        0.355611,
        0.355683,
        0.355758,
        0.355838,
        0.355921,
        0.356009,
        0.356102,
        0.356200,
        0.356302,
        0.356410,
        0.356523,
        0.356643,
        0.356768,
        0.356899,
        0.357037,
        0.357181,
        0.357333,
        0.357491,
        0.357657,
        0.357831,
        0.358012,
        0.358202,
        0.358399,
        0.358605,
        0.358820,
        0.359044,
        0.359277,
        0.359520,
        0.359772,
        0.360034,
        0.360306,
        0.360589,
        0.360882,
        0.361186,
        0.361501,
        0.361827,
        0.362165,
        0.362514,
        0.362875,
        0.363249,
        0.363635,
        0.364033,
        0.364445,
        0.364869,
        0.365307,
        0.365759,
        0.366224,
        0.366703,
        0.367197,
        0.367705,
        0.368228,
        0.368765,
        0.369318,
        0.369886,
        0.370470,
        0.371070,
        0.371685,
        0.372317,
        0.372966,
        0.373631,
        0.374314,
        0.375013,
        0.375730,
        0.376465,
        0.377218,
        0.377988,
        0.378777,
        0.379585,
        0.380412,
        0.381257,
        0.382122,
        0.383007,
        0.383911,
        0.384835,
        0.385780,
        0.386744,
        0.387730,
        0.388736,
        0.389764,
        0.390813,
        0.391883,
        0.392975,
        0.394090,
        0.395226,
        0.396385,
        0.397567,
        0.398772,
        0.400000,
        0.401251,
        0.402526,
        0.403824,
        0.405144,
        0.406488,
        0.407854,
        0.409242,
        0.410653,
        0.412086,
        0.413542,
        0.415019,
        0.416517,
        0.418038,
        0.419579,
        0.421142,
        0.422726,
        0.424331,
        0.425957,
        0.427603,
        0.429270,
        0.430957,
        0.432664,
        0.434392,
        0.436139,
        0.437906,
        0.439692,
        0.441498,
        0.443323,
        0.445167,
        0.447030,
        0.448912,
        0.450812,
        0.452731,
        0.454668,
        0.456623,
        0.458597,
        0.460588,
        0.462597,
        0.464623,
        0.466667,
        0.468727,
        0.470805,
        0.472898,
        0.475007,
        0.477131,
        0.479269,
        0.481421,
        0.483586,
        0.485763,
        0.487952,
        0.490153,
        0.492364,
        0.494586,
        0.496817,
        0.499057,
        0.501305,
        0.503561,
        0.505825,
        0.508095,
        0.510371,
        0.512652,
        0.514939,
        0.517229,
        0.519524,
        0.521821,
        0.524121,
        0.526423,
        0.528726,
        0.531029,
        0.533333,
        0.535637,
        0.537939,
        0.540241,
        0.542540,
        0.544836,
        0.547130,
        0.549420,
        0.551707,
        0.553989,
        0.556266,
        0.558537,
        0.560803,
        0.563062,
        0.565314,
        0.567559,
        0.569796,
        0.572025,
        0.574244,
        0.576454,
        0.578654,
        0.580844,
        0.583023,
        0.585190,
        0.587346,
        0.589489,
        0.591619,
        0.593736,
        0.595838,
        0.597927,
        0.600000,
        0.602058,
        0.604100,
        0.606126,
        0.608136,
        0.610129,
        0.612105,
        0.614063,
        0.616004,
        0.617926,
        0.619830,
        0.621715,
        0.623581,
        0.625427,
        0.627254,
        0.629060,
        0.630846,
        0.632611,
        0.634355,
        0.636077,
        0.637778,
        0.639456,
        0.641112,
        0.642744,
        0.644354,
        0.645940,
        0.647502,
        0.649039,
        0.650552,
        0.652040,
        0.653503,
        0.654941,
        0.656352,
        0.657737,
        0.659095,
        0.660427,
        0.661731,
        0.663007,
        0.664255,
        0.665475,
        0.666667,
        0.667829,
        0.668963,
        0.670068,
        0.671146,
        0.672196,
        0.673218,
        0.674214,
        0.675183,
        0.676126,
        0.677043,
        0.677934,
        0.678800,
        0.679642,
        0.680458,
        0.681251,
        0.682020,
        0.682766,
        0.683488,
        0.684188,
        0.684865,
        0.685521,
        0.686154,
        0.686766,
        0.687358,
        0.687928,
        0.688479,
        0.689009,
        0.689520,
        0.690012,
        0.690484,
        0.690938,
        0.691374,
        0.691792,
        0.692193,
        0.692576,
        0.692943,
        0.693293,
        0.693627,
        0.693946,
        0.694249,
        0.694536,
        0.694810,
        0.695068,
        0.695313,
        0.695544,
        0.695762,
        0.695967,
        0.696159,
        0.696340,
        0.696508,
        0.696664,
        0.696810,
        0.696944,
        0.697068,
        0.697182,
        0.697286,
        0.697380,
        0.697466,
        0.697542,
        0.697611,
        0.697671,
        0.697723,
        0.697768,
        0.697806,
        0.697838,
        0.697863,
        0.697882,
        0.697895,
        0.697904,
        0.697907,
        0.697906,
        0.697900,
        0.697891,
        0.697878,
        0.697862,
        0.697843,
        0.697821,
        0.697798,
        0.697772,
        0.697746,
        0.697718,
        0.697689,
        0.697660,
        0.697631,
        0.697603,
        0.697575,
        0.697548,
        0.697522,
        0.697498,
        0.697476,
        0.697457,
        0.697440,
        0.697426,
        0.697416,
        0.697410,
        0.697408,
        0.697410,
        0.697417,
        0.697430,
        0.697447,
        0.697471,
        0.697501,
        0.697538,
        0.697582,
        0.697633,
        0.697691,
        0.697758,
        0.697833,
        0.697917,
        0.698009,
        0.698111,
        0.698223,
        0.698345,
        0.698477,
        0.698621,
        0.698775,
        0.698941,
        0.699118,
        0.699308,
        0.699510,
        0.699726,
        0.699954,
        0.700196,
        0.700452,
        0.700722,
        0.701007,
        0.701307,
        0.701623,
        0.701953,
        0.702300,
        0.702664,
        0.703044,
        0.703441,
        0.703855,
        0.704288,
        0.704738,
        0.705207,
        0.705695,
        0.706202,
        0.706728,
        0.707275,
        0.707841,
        0.708428,
        0.709037,
        0.709666,
        0.710317,
        0.710990,
        0.711685,
        0.712403,
        0.713144,
        0.713908,
        0.714696,
        0.715508,
        0.716345,
        0.717206,
        0.718092,
        0.719004,
        0.719942,
        0.720905,
        0.721896,
        0.722913,
        0.723957,
        0.725029,
        0.726129,
        0.727257,
        0.728414,
        0.729599,
        0.730814,
        0.732059,
        0.733333,
        0.734638,
        0.735972,
        0.737335,
        0.738726,
        0.740143,
        0.741587,
        0.743056,
        0.744549,
        0.746065,
        0.747604,
        0.749165,
        0.750747,
        0.752348,
        0.753969,
        0.755608,
        0.757265,
        0.758938,
        0.760626,
        0.762330,
        0.764047,
        0.765778,
        0.767521,
        0.769275,
        0.771040,
        0.772814,
        0.774597,
        0.776388,
        0.778186,
        0.779991,
        0.781800,
        0.783614,
        0.785432,
        0.787252,
        0.789075,
        0.790898,
        0.792722,
        0.794544,
        0.796366,
        0.798184,
        0.800000,
        0.801811,
        0.803619,
        0.805421,
        0.807219,
        0.809011,
        0.810798,
        0.812580,
        0.814355,
        0.816125,
        0.817888,
        0.819645,
        0.821395,
        0.823138,
        0.824873,
        0.826602,
        0.828322,
        0.830034,
        0.831739,
        0.833434,
        0.835121,
        0.836799,
        0.838468,
        0.840128,
        0.841778,
        0.843418,
        0.845047,
        0.846667,
        0.848276,
        0.849874,
        0.851461,
        0.853036,
        0.854600,
        0.856152,
        0.857692,
        0.859220,
        0.860736,
        0.862238,
        0.863728,
        0.865204,
        0.866667,
        0.868116,
        0.869551,
        0.870973,
        0.872382,
        0.873777,
        0.875160,
        0.876529,
        0.877885,
        0.879228,
        0.880559,
        0.881877,
        0.883182,
        0.884476,
        0.885756,
        0.887025,
        0.888282,
        0.889527,
        0.890759,
        0.891981,
        0.893190,
        0.894388,
        0.895575,
        0.896750,
        0.897915,
        0.899068,
        0.900210,
        0.901341,
        0.902462,
        0.903572,
        0.904672,
        0.905761,
        0.906840,
        0.907909,
        0.908968,
        0.910016,
        0.911055,
        0.912085,
        0.913104,
        0.914115,
        0.915115,
        0.916107,
        0.917089,
        0.918063,
        0.919027,
        0.919983,
        0.920929,
        0.921868,
        0.922798,
        0.923719,
        0.924632,
        0.925537,
        0.926434,
        0.927323,
        0.928204,
        0.929078,
        0.929944,
        0.930802,
        0.931653,
        0.932497,
        0.933333,
        0.934163,
        0.934986,
        0.935801,
        0.936610,
        0.937413,
        0.938209,
        0.938999,
        0.939782,
        0.940559,
        0.941331,
        0.942096,
        0.942855,
        0.943609,
        0.944357,
        0.945100,
        0.945837,
        0.946569,
        0.947296,
        0.948018,
        0.948735,
        0.949447,
        0.950155,
        0.950858,
        0.951556,
        0.952250,
        0.952940,
        0.953625,
        0.954307,
        0.954984,
        0.955658,
        0.956328,
        0.956995,
        0.957658,
        0.958318,
        0.958974,
        0.959628,
        0.960278,
        0.960925,
        0.961570,
        0.962212,
        0.962851,
        0.963488,
        0.964122,
        0.964754,
        0.965384,
        0.966012,
        0.966638,
        0.967263,
        0.967885,
        0.968506,
        0.969126,
        0.969744,
        0.970361,
        0.970977,
        0.971592,
        0.972205,
        0.972819,
        0.973431,
        0.974043,
        0.974654,
        0.975265,
        0.975876,
        0.976486,
        0.977097,
        0.977707,
        0.978318,
        0.978929,
        0.979541,
        0.980153,
        0.980766,
        0.981379,
        0.981994,
        0.982609,
        0.983225,
        0.983843,
        0.984462,
        0.985082,
        0.985704,
        0.986328,
        0.986953,
        0.987580,
        0.988209,
        0.988841,
        0.989474,
        0.990110,
        0.990748,
        0.991389,
        0.992032,
        0.992678,
        0.993327,
        0.993979,
        0.994634,
        0.995293,
        0.995954,
        0.996619,
        0.997288,
        0.997960,
        0.998636,
        0.999316,
        0.999999
    };

    public static double hue2pitch(double hue) {
        int index1 = (int)(hue * N);
        int index2 = index1 + 1;
        if(index2 > N) {
            index2 = index1;
        }
        double hue1 = index1/((double) N);
        double hue2 = index2/((double) N);
        double p1 = hue - hue1;
        double p2 = hue2 - hue;
        double pitch;
        if((p1+p2)<=0) {
            pitch = 0;
        } else {
            pitch = (p1*hue2pitchData[index1]+p2*hue2pitchData[index2])/(p1+p2);
        }
        return pitch;
    }
}

