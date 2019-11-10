function ThirdExe( image )
%Calculation
y = optical_system(image); 
delta = zeros(300);
delta(150,150) = 1;
% Calculation delta function
L = optical_system(delta); 
% Convolution delta with ones function --> Result opt_system
result = conv2(delta,L); 

%Figures
figure;subplot(2,2,1);imshow((uint8(image)));title('\color{green}Starting Image:');hold on;
subplot(2,2,2);imshow(uint8(y));title('\color{black}After Calculation:');hold on;
subplot(2,2,3);imshow(L,[]);title('\color{red}PSF Before:');hold on;
zoom(10);
subplot(2,2,4);imshow(result,[]);title('\color{magenta}PSF After:');hold off;
zoom(10);
end

