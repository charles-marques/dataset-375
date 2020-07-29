package sivantoledo.ax25;
public class Afsk1200Filters {
  static final public int[] sample_rates = { 9600, 12000, 11025, 16000, 22050, 24000, 44100, 48000 };
  static final public int[] bit_periods = { 8, 10, 9, 13, 18, 20, 36, 40 };
  static final public float[][][] time_domain_filter_full = {
   {
    { -8.538057e-002f, -1.606386e-001f, -2.562661e-002f, 1.780909e-001f, 1.780909e-001f, -2.562661e-002f, -1.606386e-001f, -8.538057e-002f },
    { -5.622111e-002f, -1.260336e-001f, -9.508627e-002f, 3.338428e-002f, 1.523783e-001f, 1.523783e-001f, 3.338428e-002f, -9.508627e-002f, -1.260336e-001f, -5.622111e-002f },
    { -7.586213e-002f, -1.412157e-001f, -6.605851e-002f, 9.922325e-002f, 1.856153e-001f, 9.922325e-002f, -6.605851e-002f, -1.412157e-001f, -7.586213e-002f },
    { -4.165569e-002f, -8.654256e-002f, -9.469657e-002f, -5.238007e-002f, 2.472920e-002f, 9.802254e-002f, 1.279778e-001f, 9.802254e-002f, 2.472920e-002f, -5.238007e-002f, -9.469657e-002f, -8.654256e-002f, -4.165569e-002f },
    { -2.364670e-002f, -5.043069e-002f, -6.796924e-002f, -6.863212e-002f, -4.951926e-002f, -1.398200e-002f, 2.897697e-002f, 6.733815e-002f, 8.988417e-002f, 8.988417e-002f, 6.733815e-002f, 2.897697e-002f, -1.398200e-002f, -4.951926e-002f, -6.863212e-002f, -6.796924e-002f, -5.043069e-002f, -2.364670e-002f },
    { -1.595292e-002f, -3.931281e-002f, -5.770766e-002f, -6.496202e-002f, -5.733060e-002f, -3.487383e-002f, -1.778508e-003f, 3.454271e-002f, 6.535965e-002f, 8.300178e-002f, 8.300178e-002f, 6.535965e-002f, 3.454271e-002f, -1.778508e-003f, -3.487383e-002f, -5.733060e-002f, -6.496202e-002f, -5.770766e-002f, -3.931281e-002f, -1.595292e-002f },
    { -8.261884e-003f, -1.530739e-002f, -2.208928e-002f, -2.799960e-002f, -3.245890e-002f, -3.497078e-002f, -3.517137e-002f, -3.286900e-002f, -2.806984e-002f, -2.098659e-002f, -1.202899e-002f, -1.776253e-003f, 9.066339e-003f, 1.972315e-002f, 2.941143e-002f, 3.740638e-002f, 4.310223e-002f, 4.606384e-002f, 4.606384e-002f, 4.310223e-002f, 3.740638e-002f, 2.941143e-002f, 1.972315e-002f, 9.066339e-003f, -1.776253e-003f, -1.202899e-002f, -2.098659e-002f, -2.806984e-002f, -3.286900e-002f, -3.517137e-002f, -3.497078e-002f, -3.245890e-002f, -2.799960e-002f, -2.208928e-002f, -1.530739e-002f, -8.261884e-003f },
    { -5.028936e-003f, -1.090705e-002f, -1.680680e-002f, -2.230031e-002f, -2.695781e-002f, -3.038067e-002f, -3.223361e-002f, -3.227344e-002f, -3.037178e-002f, -2.652998e-002f, -2.088457e-002f, -1.370266e-002f, -5.367345e-003f, 3.646094e-003f, 1.280138e-002f, 2.153775e-002f, 2.930895e-002f, 3.562187e-002f, 4.007171e-002f, 4.237116e-002f, 4.237116e-002f, 4.007171e-002f, 3.562187e-002f, 2.930895e-002f, 2.153775e-002f, 1.280138e-002f, 3.646094e-003f, -5.367345e-003f, -1.370266e-002f, -2.088457e-002f, -2.652998e-002f, -3.037178e-002f, -3.227344e-002f, -3.223361e-002f, -3.038067e-002f, -2.695781e-002f, -2.230031e-002f, -1.680680e-002f, -1.090705e-002f, -5.028936e-003f }
   },
   {
    { -2.185257e-002f, -6.703124e-003f, 6.574072e-002f, 6.194076e-002f, -7.181203e-002f, -1.592129e-001f, -3.565027e-002f, 1.616405e-001f, 1.616405e-001f, -3.565027e-002f, -1.592129e-001f, -7.181203e-002f, 6.194076e-002f, 6.574072e-002f, -6.703124e-003f, -2.185257e-002f },
    { -1.512995e-002f, -1.638177e-002f, 2.300329e-002f, 6.330897e-002f, 4.257491e-002f, -4.503183e-002f, -1.218371e-001f, -9.857633e-002f, 2.362166e-002f, 1.391256e-001f, 1.391256e-001f, 2.362166e-002f, -9.857633e-002f, -1.218371e-001f, -4.503183e-002f, 4.257491e-002f, 6.330897e-002f, 2.300329e-002f, -1.638177e-002f, -1.512995e-002f },
    { -2.064753e-002f, -7.677662e-003f, 4.760857e-002f, 6.827848e-002f, -6.116157e-003f, -1.150274e-001f, -1.245511e-001f, 5.302535e-003f, 1.482799e-001f, 1.482799e-001f, 5.302535e-003f, -1.245511e-001f, -1.150274e-001f, -6.116157e-003f, 6.827848e-002f, 4.760857e-002f, -7.677662e-003f, -2.064753e-002f },
    { -1.279020e-002f, -1.401851e-002f, 3.287701e-003f, 3.091304e-002f, 4.859587e-002f, 3.723111e-002f, -5.806838e-003f, -6.065027e-002f, -9.437141e-002f, -8.135381e-002f, -2.153914e-002f, 5.640279e-002f, 1.105078e-001f, 1.105078e-001f, 5.640279e-002f, -2.153914e-002f, -8.135381e-002f, -9.437141e-002f, -6.065027e-002f, -5.806838e-003f, 3.723111e-002f, 4.859587e-002f, 3.091304e-002f, 3.287701e-003f, -1.401851e-002f, -1.279020e-002f },
    { -7.792168e-003f, -1.135158e-002f, -8.224556e-003f, 2.012026e-003f, 1.651861e-002f, 2.980923e-002f, 3.560486e-002f, 2.931803e-002f, 1.022578e-002f, -1.759227e-002f, -4.607374e-002f, -6.562544e-002f, -6.842752e-002f, -5.141378e-002f, -1.778817e-002f, 2.358239e-002f, 6.080124e-002f, 8.274861e-002f, 8.274861e-002f, 6.080124e-002f, 2.358239e-002f, -1.778817e-002f, -5.141378e-002f, -6.842752e-002f, -6.562544e-002f, -4.607374e-002f, -1.759227e-002f, 1.022578e-002f, 2.931803e-002f, 3.560486e-002f, 2.980923e-002f, 1.651861e-002f, 2.012026e-003f, -8.224556e-003f, -1.135158e-002f, -7.792168e-003f },
    { -4.702692e-003f, -9.502095e-003f, -1.005093e-002f, -4.953291e-003f, 5.202632e-003f, 1.771241e-002f, 2.837769e-002f, 3.275849e-002f, 2.769259e-002f, 1.258595e-002f, -9.994799e-003f, -3.470396e-002f, -5.474560e-002f, -6.380472e-002f, -5.801069e-002f, -3.731193e-002f, -5.791305e-003f, 2.923712e-002f, 5.913253e-002f, 7.629442e-002f, 7.629442e-002f, 5.913253e-002f, 2.923712e-002f, -5.791305e-003f, -3.731193e-002f, -5.801069e-002f, -6.380472e-002f, -5.474560e-002f, -3.470396e-002f, -9.994799e-003f, 1.258595e-002f, 2.769259e-002f, 3.275849e-002f, 2.837769e-002f, 1.771241e-002f, 5.202632e-003f, -4.953291e-003f, -1.005093e-002f, -9.502095e-003f, -4.702692e-003f },
    { -3.076732e-003f, -4.580748e-003f, -5.498025e-003f, -5.631505e-003f, -4.856768e-003f, -3.142627e-003f, -5.631590e-004f, 2.700709e-003f, 6.370970e-003f, 1.009341e-002f, 1.346813e-002f, 1.608716e-002f, 1.757579e-002f, 1.763350e-002f, 1.607036e-002f, 1.283499e-002f, 8.030552e-003f, 1.916793e-003f, -5.103076e-003f, -1.251007e-002f, -1.970977e-002f, -2.608200e-002f, -3.103558e-002f, -3.406302e-002f, -3.479015e-002f, -3.301564e-002f, -2.873649e-002f, -2.215653e-002f, -1.367671e-002f, -3.867528e-003f, 6.574365e-003f, 1.688170e-002f, 2.627951e-002f, 3.404991e-002f, 3.959275e-002f, 4.247681e-002f, 4.247681e-002f, 3.959275e-002f, 3.404991e-002f, 2.627951e-002f, 1.688170e-002f, 6.574365e-003f, -3.867528e-003f, -1.367671e-002f, -2.215653e-002f, -2.873649e-002f, -3.301564e-002f, -3.479015e-002f, -3.406302e-002f, -3.103558e-002f, -2.608200e-002f, -1.970977e-002f, -1.251007e-002f, -5.103076e-003f, 1.916793e-003f, 8.030552e-003f, 1.283499e-002f, 1.607036e-002f, 1.763350e-002f, 1.757579e-002f, 1.608716e-002f, 1.346813e-002f, 1.009341e-002f, 6.370970e-003f, 2.700709e-003f, -5.631590e-004f, -3.142627e-003f, -4.856768e-003f, -5.631505e-003f, -5.498025e-003f, -4.580748e-003f, -3.076732e-003f },
    { -1.555936e-003f, -3.074922e-003f, -4.298532e-003f, -5.055988e-003f, -5.207867e-003f, -4.661980e-003f, -3.386024e-003f, -1.415645e-003f, 1.143128e-003f, 4.117646e-003f, 7.278662e-003f, 1.035612e-002f, 1.305964e-002f, 1.510224e-002f, 1.622537e-002f, 1.622320e-002f, 1.496402e-002f, 1.240675e-002f, 8.611013e-003f, 3.739340e-003f, -1.948784e-003f, -8.111153e-003f, -1.434449e-002f, -2.021209e-002f, -2.527549e-002f, -2.912772e-002f, -3.142564e-002f, -3.191868e-002f, -3.047148e-002f, -2.707872e-002f, -2.187043e-002f, -1.510729e-002f, -7.165972e-003f, 1.484604e-003f, 1.031443e-002f, 1.876872e-002f, 2.630674e-002f, 3.244019e-002f, 3.676812e-002f, 3.900592e-002f, 3.900592e-002f, 3.676812e-002f, 3.244019e-002f, 2.630674e-002f, 1.876872e-002f, 1.031443e-002f, 1.484604e-003f, -7.165972e-003f, -1.510729e-002f, -2.187043e-002f, -2.707872e-002f, -3.047148e-002f, -3.191868e-002f, -3.142564e-002f, -2.912772e-002f, -2.527549e-002f, -2.021209e-002f, -1.434449e-002f, -8.111153e-003f, -1.948784e-003f, 3.739340e-003f, 8.611013e-003f, 1.240675e-002f, 1.496402e-002f, 1.622320e-002f, 1.622537e-002f, 1.510224e-002f, 1.305964e-002f, 1.035612e-002f, 7.278662e-003f, 4.117646e-003f, 1.143128e-003f, -1.415645e-003f, -3.386024e-003f, -4.661980e-003f, -5.207867e-003f, -5.055988e-003f, -4.298532e-003f, -3.074922e-003f, -1.555936e-003f }
   }
  };
  static final public float[][][] time_domain_filter_none = {
   {
    { -1.339009e-001f, -2.058943e-001f, -1.939428e-002f, 2.397803e-001f, 2.397803e-001f, -1.939428e-002f, -2.058943e-001f, -1.339009e-001f },
    { -9.317707e-002f, -1.674304e-001f, -1.144164e-001f, 5.382636e-002f, 2.042595e-001f, 2.042595e-001f, 5.382636e-002f, -1.144164e-001f, -1.674304e-001f, -9.317707e-002f },
    { -1.178083e-001f, -1.834575e-001f, -7.457574e-002f, 1.382653e-001f, 2.471472e-001f, 1.382653e-001f, -7.457574e-002f, -1.834575e-001f, -1.178083e-001f },
    { -6.883035e-002f, -1.183793e-001f, -1.202390e-001f, -6.050761e-002f, 3.973044e-002f, 1.327173e-001f, 1.703936e-001f, 1.327173e-001f, 3.973044e-002f, -6.050761e-002f, -1.202390e-001f, -1.183793e-001f, -6.883035e-002f },
    { -4.227421e-002f, -7.290506e-002f, -9.066040e-002f, -8.705643e-002f, -5.936009e-002f, -1.225971e-002f, 4.294760e-002f, 9.153592e-002f, 1.198955e-001f, 1.198955e-001f, 9.153592e-002f, 4.294760e-002f, -1.225971e-002f, -5.936009e-002f, -8.705643e-002f, -9.066040e-002f, -7.290506e-002f, -4.227421e-002f },
    { -3.222841e-002f, -5.937048e-002f, -7.900318e-002f, -8.418689e-002f, -7.099170e-002f, -4.003098e-002f, 3.238984e-003f, 4.963661e-002f, 8.853456e-002f, 1.106705e-001f, 1.106705e-001f, 8.853456e-002f, 4.963661e-002f, 3.238984e-003f, -4.003098e-002f, -7.099170e-002f, -8.418689e-002f, -7.900318e-002f, -5.937048e-002f, -3.222841e-002f },
    { -1.688675e-002f, -2.522894e-002f, -3.298569e-002f, -3.945459e-002f, -4.397690e-002f, -4.600012e-002f, -4.513445e-002f, -4.119757e-002f, -3.424285e-002f, -2.456778e-002f, -1.270102e-002f, 6.314195e-004f, 1.455858e-002f, 2.813110e-002f, 4.039641e-002f, 5.047587e-002f, 5.763707e-002f, 6.135475e-002f, 6.135475e-002f, 5.763707e-002f, 5.047587e-002f, 4.039641e-002f, 2.813110e-002f, 1.455858e-002f, 6.314195e-004f, -1.270102e-002f, -2.456778e-002f, -3.424285e-002f, -4.119757e-002f, -4.513445e-002f, -4.600012e-002f, -4.397690e-002f, -3.945459e-002f, -3.298569e-002f, -2.522894e-002f, -1.688675e-002f },
    { -1.254927e-002f, -1.960191e-002f, -2.645696e-002f, -3.261764e-002f, -3.759252e-002f, -4.093339e-002f, -4.227201e-002f, -4.135251e-002f, -3.805685e-002f, -3.242095e-002f, -2.463990e-002f, -1.506147e-002f, -4.167957e-003f, 7.452467e-003f, 1.914302e-002f, 3.022160e-002f, 4.002689e-002f, 4.796383e-002f, 5.354507e-002f, 5.642521e-002f, 5.642521e-002f, 5.354507e-002f, 4.796383e-002f, 4.002689e-002f, 3.022160e-002f, 1.914302e-002f, 7.452467e-003f, -4.167957e-003f, -1.506147e-002f, -2.463990e-002f, -3.242095e-002f, -3.805685e-002f, -4.135251e-002f, -4.227201e-002f, -4.093339e-002f, -3.759252e-002f, -3.261764e-002f, -2.645696e-002f, -1.960191e-002f, -1.254927e-002f }
   },
   {
    { -2.084545e-003f, 2.254878e-002f, 7.962869e-002f, 3.712606e-002f, -1.261127e-001f, -2.000219e-001f, -2.410548e-002f, 2.206759e-001f, 2.206759e-001f, -2.410548e-002f, -2.000219e-001f, -1.261127e-001f, 3.712606e-002f, 7.962869e-002f, 2.254878e-002f, -2.084545e-003f },
    { -6.220226e-004f, 6.246309e-003f, 4.336082e-002f, 6.631613e-002f, 1.945471e-002f, -8.739570e-002f, -1.618851e-001f, -1.124118e-001f, 4.660931e-002f, 1.883459e-001f, 1.883459e-001f, 4.660931e-002f, -1.124118e-001f, -1.618851e-001f, -8.739570e-002f, 1.945471e-002f, 6.631613e-002f, 4.336082e-002f, 6.246309e-003f, -6.220226e-004f },
    { -2.395635e-003f, 1.813050e-002f, 6.408293e-002f, 5.727940e-002f, -4.612471e-002f, -1.622780e-001f, -1.473014e-001f, 2.517136e-002f, 2.005980e-001f, 2.005980e-001f, 2.517136e-002f, -1.473014e-001f, -1.622780e-001f, -4.612471e-002f, 5.727940e-002f, 6.408293e-002f, 1.813050e-002f, -2.395635e-003f },
    { -1.236077e-003f, 2.548709e-003f, 2.082463e-002f, 4.296432e-002f, 4.876612e-002f, 2.229715e-002f, -3.371153e-002f, -9.400324e-002f, -1.225961e-001f, -9.462111e-002f, -1.448180e-002f, 8.254569e-002f, 1.481262e-001f, 1.481262e-001f, 8.254569e-002f, -1.448180e-002f, -9.462111e-002f, -1.225961e-001f, -9.400324e-002f, -3.371153e-002f, 2.229715e-002f, 4.876612e-002f, 4.296432e-002f, 2.082463e-002f, 2.548709e-003f, -1.236077e-003f },
    { -2.348621e-004f, -8.188409e-004f, 4.322591e-003f, 1.472729e-002f, 2.692352e-002f, 3.529607e-002f, 3.407530e-002f, 1.978998e-002f, -6.762944e-003f, -3.990836e-002f, -7.028280e-002f, -8.760362e-002f, -8.415882e-002f, -5.779760e-002f, -1.328262e-002f, 3.861673e-002f, 8.412538e-002f, 1.106300e-001f, 1.106300e-001f, 8.412538e-002f, 3.861673e-002f, -1.328262e-002f, -5.779760e-002f, -8.415882e-002f, -8.760362e-002f, -7.028280e-002f, -3.990836e-002f, -6.762944e-003f, 1.978998e-002f, 3.407530e-002f, 3.529607e-002f, 2.692352e-002f, 1.472729e-002f, 4.322591e-003f, -8.188409e-004f, -2.348621e-004f },
    { 1.235916e-003f, -1.014480e-003f, 5.589534e-004f, 6.739477e-003f, 1.638179e-002f, 2.643799e-002f, 3.270933e-002f, 3.116178e-002f, 1.941992e-002f, -2.058635e-003f, -2.957644e-002f, -5.679531e-002f, -7.626852e-002f, -8.151785e-002f, -6.905449e-002f, -3.971027e-002f, 1.183533e-003f, 4.490369e-002f, 8.147414e-002f, 1.022567e-001f, 1.022567e-001f, 8.147414e-002f, 4.490369e-002f, 1.183533e-003f, -3.971027e-002f, -6.905449e-002f, -8.151785e-002f, -7.626852e-002f, -5.679531e-002f, -2.957644e-002f, -2.058635e-003f, 1.941992e-002f, 3.116178e-002f, 3.270933e-002f, 2.643799e-002f, 1.638179e-002f, 6.739477e-003f, 5.589534e-004f, -1.014480e-003f, 1.235916e-003f },
    { 3.050483e-004f, -4.120990e-004f, -5.782374e-004f, -5.135811e-005f, 1.234737e-003f, 3.254133e-003f, 5.881370e-003f, 8.894417e-003f, 1.198857e-002f, 1.480067e-002f, 1.694188e-002f, 1.803627e-002f, 1.776163e-002f, 1.588847e-002f, 1.231317e-002f, 7.081354e-003f, 3.986663e-004f, -7.373145e-003f, -1.573535e-002f, -2.408573e-002f, -3.176402e-002f, -3.810588e-002f, -4.250057e-002f, -4.444736e-002f, -4.360521e-002f, -3.983105e-002f, -3.320258e-002f, -2.402286e-002f, -1.280579e-002f, -2.429808e-004f, 1.284560e-002f, 2.557315e-002f, 3.705480e-002f, 4.647766e-002f, 5.316604e-002f, 5.663631e-002f, 5.663631e-002f, 5.316604e-002f, 4.647766e-002f, 3.705480e-002f, 2.557315e-002f, 1.284560e-002f, -2.429808e-004f, -1.280579e-002f, -2.402286e-002f, -3.320258e-002f, -3.983105e-002f, -4.360521e-002f, -4.444736e-002f, -4.250057e-002f, -3.810588e-002f, -3.176402e-002f, -2.408573e-002f, -1.573535e-002f, -7.373145e-003f, 3.986663e-004f, 7.081354e-003f, 1.231317e-002f, 1.588847e-002f, 1.776163e-002f, 1.803627e-002f, 1.694188e-002f, 1.480067e-002f, 1.198857e-002f, 8.894417e-003f, 5.881370e-003f, 3.254133e-003f, 1.234737e-003f, -5.135811e-005f, -5.782374e-004f, -4.120990e-004f, 3.050483e-004f },
    { 1.090725e-003f, 2.145359e-004f, -3.671281e-004f, -5.168203e-004f, -1.324122e-004f, 8.390482e-004f, 2.391043e-003f, 4.452077e-003f, 6.885825e-003f, 9.497335e-003f, 1.204518e-002f, 1.425887e-002f, 1.586042e-002f, 1.658843e-002f, 1.622265e-002f, 1.460720e-002f, 1.167002e-002f, 7.436988e-003f, 2.038835e-003f, -4.289859e-003f, -1.122021e-002f, -1.834677e-002f, -2.521240e-002f, -3.133890e-002f, -3.626095e-002f, -3.956110e-002f, -4.090303e-002f, -4.006032e-002f, -3.693852e-002f, -3.158836e-002f, -2.420895e-002f, -1.514021e-002f, -4.844976e-003f, 6.118407e-003f, 1.713096e-002f, 2.755318e-002f, 3.676743e-002f, 4.421957e-002f, 4.945669e-002f, 5.215826e-002f, 5.215826e-002f, 4.945669e-002f, 4.421957e-002f, 3.676743e-002f, 2.755318e-002f, 1.713096e-002f, 6.118407e-003f, -4.844976e-003f, -1.514021e-002f, -2.420895e-002f, -3.158836e-002f, -3.693852e-002f, -4.006032e-002f, -4.090303e-002f, -3.956110e-002f, -3.626095e-002f, -3.133890e-002f, -2.521240e-002f, -1.834677e-002f, -1.122021e-002f, -4.289859e-003f, 2.038835e-003f, 7.436988e-003f, 1.167002e-002f, 1.460720e-002f, 1.622265e-002f, 1.658843e-002f, 1.586042e-002f, 1.425887e-002f, 1.204518e-002f, 9.497335e-003f, 6.885825e-003f, 4.452077e-003f, 2.391043e-003f, 8.390482e-004f, -1.324122e-004f, -5.168203e-004f, -3.671281e-004f, 2.145359e-004f, 1.090725e-003f }
   }
  };
  static final public float[][][] corr_diff_filter = {
   {
    { 3.560173e-003f, 3.808372e-002f, 1.610319e-001f, 2.973243e-001f, 2.973243e-001f, 1.610319e-001f, 3.808372e-002f, 3.560173e-003f },
    { 2.199047e-003f, 1.735971e-002f, 7.367287e-002f, 1.662386e-001f, 2.405298e-001f, 2.405298e-001f, 1.662386e-001f, 7.367287e-002f, 1.735971e-002f, 2.199047e-003f },
    { 3.225875e-003f, 2.591391e-002f, 1.079899e-001f, 2.232384e-001f, 2.792639e-001f, 2.232384e-001f, 1.079899e-001f, 2.591391e-002f, 3.225875e-003f },
    { 1.649749e-003f, 8.019771e-003f, 2.951243e-002f, 7.118514e-002f, 1.247140e-001f, 1.705763e-001f, 1.886853e-001f, 1.705763e-001f, 1.247140e-001f, 7.118514e-002f, 2.951243e-002f, 8.019771e-003f, 1.649749e-003f },
    { 8.636339e-004f, 3.182517e-003f, 9.639032e-003f, 2.284951e-002f, 4.353592e-002f, 6.976907e-002f, 9.715855e-002f, 1.199990e-001f, 1.330028e-001f, 1.330028e-001f, 1.199990e-001f, 9.715855e-002f, 6.976907e-002f, 4.353592e-002f, 2.284951e-002f, 9.639032e-003f, 3.182517e-003f, 8.636339e-004f },
    { 5.110546e-004f, 2.174060e-003f, 6.473724e-003f, 1.533688e-002f, 2.975306e-002f, 4.921456e-002f, 7.157931e-002f, 9.343798e-002f, 1.109054e-001f, 1.206140e-001f, 1.206140e-001f, 1.109054e-001f, 9.343798e-002f, 7.157931e-002f, 4.921456e-002f, 2.975306e-002f, 1.533688e-002f, 6.473724e-003f, 2.174060e-003f, 5.110546e-004f },
    { 2.639688e-004f, 6.466073e-004f, 1.289839e-003f, 2.384937e-003f, 4.112716e-003f, 6.622334e-003f, 1.001174e-002f, 1.431186e-002f, 1.947623e-002f, 2.537743e-002f, 3.181093e-002f, 3.850630e-002f, 4.514497e-002f, 5.138316e-002f, 5.687791e-002f, 6.131400e-002f, 6.442924e-002f, 6.603583e-002f, 6.603583e-002f, 6.442924e-002f, 6.131400e-002f, 5.687791e-002f, 5.138316e-002f, 4.514497e-002f, 3.850630e-002f, 3.181093e-002f, 2.537743e-002f, 1.947623e-002f, 1.431186e-002f, 1.001174e-002f, 6.622334e-003f, 4.112716e-003f, 2.384937e-003f, 1.289839e-003f, 6.466073e-004f, 2.639688e-004f },
    { 1.230588e-004f, 4.146753e-004f, 8.667268e-004f, 1.606401e-003f, 2.759319e-003f, 4.437769e-003f, 6.729266e-003f, 9.686389e-003f, 1.331883e-002f, 1.758836e-002f, 2.240733e-002f, 2.764087e-002f, 3.311284e-002f, 3.861510e-002f, 4.391962e-002f, 4.879247e-002f, 5.300873e-002f, 5.636712e-002f, 5.870346e-002f, 5.990168e-002f, 5.990168e-002f, 5.870346e-002f, 5.636712e-002f, 5.300873e-002f, 4.879247e-002f, 4.391962e-002f, 3.861510e-002f, 3.311284e-002f, 2.764087e-002f, 2.240733e-002f, 1.758836e-002f, 1.331883e-002f, 9.686389e-003f, 6.729266e-003f, 4.437769e-003f, 2.759319e-003f, 1.606401e-003f, 8.667268e-004f, 4.146753e-004f, 1.230588e-004f }
   },
   {
    { -1.296358e-003f, -5.406338e-003f, -1.238714e-002f, -1.074497e-002f, 2.042052e-002f, 9.036964e-002f, 1.784209e-001f, 2.406237e-001f, 2.406237e-001f, 1.784209e-001f, 9.036964e-002f, 2.042052e-002f, -1.074497e-002f, -1.238714e-002f, -5.406338e-003f, -1.296358e-003f },
    { -8.259212e-004f, -3.169595e-003f, -7.490150e-003f, -1.139292e-002f, -7.615798e-003f, 1.259730e-002f, 5.317232e-002f, 1.081085e-001f, 1.616906e-001f, 1.949256e-001f, 1.949256e-001f, 1.616906e-001f, 1.081085e-001f, 5.317232e-002f, 1.259730e-002f, -7.615798e-003f, -1.139292e-002f, -7.490150e-003f, -3.169595e-003f, -8.259212e-004f },
    { -1.354857e-003f, -4.302660e-003f, -9.432808e-003f, -1.124037e-002f, 2.250991e-003f, 4.111857e-002f, 1.028676e-001f, 1.686674e-001f, 2.114261e-001f, 2.114261e-001f, 1.686674e-001f, 1.028676e-001f, 4.111857e-002f, 2.250991e-003f, -1.124037e-002f, -9.432808e-003f, -4.302660e-003f, -1.354857e-003f },
    { -7.776167e-004f, -1.982913e-003f, -4.025207e-003f, -6.651467e-003f, -8.336999e-003f, -6.445344e-003f, 1.958825e-003f, 1.888766e-002f, 4.426837e-002f, 7.535189e-002f, 1.070253e-001f, 1.330266e-001f, 1.477009e-001f, 1.477009e-001f, 1.330266e-001f, 1.070253e-001f, 7.535189e-002f, 4.426837e-002f, 1.888766e-002f, 1.958825e-003f, -6.445344e-003f, -8.336999e-003f, -6.651467e-003f, -4.025207e-003f, -1.982913e-003f, -7.776167e-004f },
    { -4.275563e-004f, -1.005197e-003f, -1.862639e-003f, -3.080131e-003f, -4.529092e-003f, -5.820210e-003f, -6.316238e-003f, -5.216578e-003f, -1.703686e-003f, 4.874847e-003f, 1.482584e-002f, 2.797246e-002f, 4.359346e-002f, 6.046098e-002f, 7.697822e-002f, 9.139795e-002f, 1.020847e-001f, 1.077729e-001f, 1.077729e-001f, 1.020847e-001f, 9.139795e-002f, 7.697822e-002f, 6.046098e-002f, 4.359346e-002f, 2.797246e-002f, 1.482584e-002f, 4.874847e-003f, -1.703686e-003f, -5.216578e-003f, -6.316238e-003f, -5.820210e-003f, -4.529092e-003f, -3.080131e-003f, -1.862639e-003f, -1.005197e-003f, -4.275563e-004f },
    { -2.035376e-004f, -6.689791e-004f, -1.328527e-003f, -2.272442e-003f, -3.481130e-003f, -4.781697e-003f, -5.833453e-003f, -6.149991e-003f, -5.158507e-003f, -2.289504e-003f, 2.916797e-003f, 1.070557e-002f, 2.102369e-002f, 3.347458e-002f, 4.732339e-002f, 6.155612e-002f, 7.498701e-002f, 8.640007e-002f, 9.470396e-002f, 9.907658e-002f, 9.907658e-002f, 9.470396e-002f, 8.640007e-002f, 7.498701e-002f, 6.155612e-002f, 4.732339e-002f, 3.347458e-002f, 2.102369e-002f, 1.070557e-002f, 2.916797e-003f, -2.289504e-003f, -5.158507e-003f, -6.149991e-003f, -5.833453e-003f, -4.781697e-003f, -3.481130e-003f, -2.272442e-003f, -1.328527e-003f, -6.689791e-004f, -2.035376e-004f },
    { -1.515930e-004f, -2.822090e-004f, -4.354332e-004f, -6.231711e-004f, -8.542967e-004f, -1.132997e-003f, -1.457305e-003f, -1.817934e-003f, -2.197524e-003f, -2.570369e-003f, -2.902690e-003f, -3.153472e-003f, -3.275862e-003f, -3.219077e-003f, -2.930750e-003f, -2.359614e-003f, -1.458378e-003f, -1.866640e-004f, 1.486172e-003f, 3.578478e-003f, 6.094172e-003f, 9.021158e-003f, 1.233042e-002f, 1.597587e-002f, 1.989496e-002f, 2.401016e-002f, 2.823111e-002f, 3.245754e-002f, 3.658270e-002f, 4.049722e-002f, 4.409326e-002f, 4.726869e-002f, 4.993109e-002f, 5.200155e-002f, 5.341783e-002f, 5.413695e-002f, 5.413695e-002f, 5.341783e-002f, 5.200155e-002f, 4.993109e-002f, 4.726869e-002f, 4.409326e-002f, 4.049722e-002f, 3.658270e-002f, 3.245754e-002f, 2.823111e-002f, 2.401016e-002f, 1.989496e-002f, 1.597587e-002f, 1.233042e-002f, 9.021158e-003f, 6.094172e-003f, 3.578478e-003f, 1.486172e-003f, -1.866640e-004f, -1.458378e-003f, -2.359614e-003f, -2.930750e-003f, -3.219077e-003f, -3.275862e-003f, -3.153472e-003f, -2.902690e-003f, -2.570369e-003f, -2.197524e-003f, -1.817934e-003f, -1.457305e-003f, -1.132997e-003f, -8.542967e-004f, -6.231711e-004f, -4.354332e-004f, -2.822090e-004f, -1.515930e-004f },
    { -5.038663e-005f, -1.566090e-004f, -2.776591e-004f, -4.222836e-004f, -5.979724e-004f, -8.099774e-004f, -1.060382e-003f, -1.347281e-003f, -1.664124e-003f, -1.999269e-003f, -2.335792e-003f, -2.651573e-003f, -2.919681e-003f, -3.109052e-003f, -3.185454e-003f, -3.112703e-003f, -2.854091e-003f, -2.373975e-003f, -1.639451e-003f, -6.220534e-004f, 7.006086e-004f, 2.343343e-003f, 4.312078e-003f, 6.602848e-003f, 9.201100e-003f, 1.208140e-002f, 1.520752e-002f, 1.853299e-002f, 2.200202e-002f, 2.555088e-002f, 2.910955e-002f, 3.260375e-002f, 3.595715e-002f, 3.909375e-002f, 4.194031e-002f, 4.442872e-002f, 4.649828e-002f, 4.809774e-002f, 4.918705e-002f, 4.973870e-002f, 4.973870e-002f, 4.918705e-002f, 4.809774e-002f, 4.649828e-002f, 4.442872e-002f, 4.194031e-002f, 3.909375e-002f, 3.595715e-002f, 3.260375e-002f, 2.910955e-002f, 2.555088e-002f, 2.200202e-002f, 1.853299e-002f, 1.520752e-002f, 1.208140e-002f, 9.201100e-003f, 6.602848e-003f, 4.312078e-003f, 2.343343e-003f, 7.006086e-004f, -6.220534e-004f, -1.639451e-003f, -2.373975e-003f, -2.854091e-003f, -3.112703e-003f, -3.185454e-003f, -3.109052e-003f, -2.919681e-003f, -2.651573e-003f, -2.335792e-003f, -1.999269e-003f, -1.664124e-003f, -1.347281e-003f, -1.060382e-003f, -8.099774e-004f, -5.979724e-004f, -4.222836e-004f, -2.776591e-004f, -1.566090e-004f, -5.038663e-005f }
   }
  };
}