function PrintSecond(image,desiredPercent)
factor= 0.1:3:desiredPercent;
sumError1=zeros(1,length(factor));
sumError2=zeros(1,length(factor));
%Calculation of Mean error 
 for i = 1 : length(factor)
    [ MOerror1,MOerror2,result1,result2 ] = SecondExe_2( image, factor(i) );
     sumError1(i) = sumError1(i) + MOerror1;
     sumError2(i) = sumError2(i) + MOerror2;

 end
%Figures
figure('units','normalized','outerposition',[0 0 1 1]);subplot(2,2,1);imshow(uint8(image));title('\color{green}Starting Image:');hold on;
subplot(2,2,2);imshow(uint8(real(result1)));title('\color{magenta}After Reconstruction (IFFT2):');hold on;
subplot(2,2,3);imshow(uint8(real(result2)));title('\color{blue}After Reconstruction (IDCT2) :');hold on;
subplot(2,2,4);plot(factor,sumError1,factor,sumError2);legend('Mean Error Value of FFT2','Mean Error Value of DCT2');title('\color{cyan}Mean Error Values:');hold off;


end

