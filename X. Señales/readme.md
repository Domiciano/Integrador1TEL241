Este código de Java calcula la FFT de una señal
```
public class Main {

    public static void main(String[] args) {
        int sampling_rate = 1000;
        double[] time = new double[sampling_rate*10];
        double[] signal = new double[sampling_rate*10];
        
        for(int i=0; i<time.length; i++){
            time[i] = i*1.0/sampling_rate;
            signal[i] = Math.sin(2*Math.PI*100*time[i]) + 0.5*Math.sin(2*Math.PI*200*time[i]);
        }
        
        double[] FFT = calculateFFT(signal);
        double[] freqs = calculateFreqs(signal.length, 1.0/sampling_rate);
        
        for(int i=0; i<FFT.length; i++){
            System.out.println(freqs[i] + " : " + FFT[i]);
        }
    }
    
    public static double[] calculateFFT(double[] signal){
        double[] FFT = new double[signal.length];
        
        for(int i=0; i<signal.length; i++){
            double real = 0.0;
            double imag = 0.0;
            
            for(int j=0; j<signal.length; j++){
                double angle = 2*Math.PI*i*j/signal.length;
                real += signal[j]*Math.cos(angle);
                imag -= signal[j]*Math.sin(angle);
            }
            
            FFT[i] = Math.sqrt(real*real + imag*imag);
        }
        
        return FFT;
    }
    
    public static double[] calculateFreqs(int n, double T){
        double[] freqs = new double[n];
        double delta_f = 1.0/(n*T);
        
        for(int i=0; i<n; i++){
            if(i < n/2){
                freqs[i] = i*delta_f;
            }
            else{
                freqs[i] = (i-n)*delta_f;
            }
        }
        
        return freqs;
    }

}
```
