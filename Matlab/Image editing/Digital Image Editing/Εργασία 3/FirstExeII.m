function FirstExeII( image )
%Image rotate 45 degrees
image = imrotate(image,45);
%FFT2
newimage = fft2(image);
%FFTshift
newimage = fftshift(newimage);

L = Balance(newimage);

figure;subplot(2,2,1);imshow(uint8(image));title('\color{green}Startin Image:');hold on;
subplot(2,2,2);imshow(uint8(newimage));title('\color{red}Image After FFT and Shift:');hold on;
subplot(2,2,3);imshow(L);title('\color{orange}Range normal plot:');hold on;
subplot(2,2,4);imshow(log(L+1));title('\color{magenta}Range log plot:');hold off;

end

