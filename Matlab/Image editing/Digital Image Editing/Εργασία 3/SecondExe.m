function  SecondExe( image, D0, n )
multipliedimage = zeros(2*size(image,1),2*size(image,2));
D = zeros(2*size(image,1),2*size(image,2));
H = zeros(2*size(image,1),2*size(image,2));
%Add padding to image
paddedimage = zeros(2*size(image,1),2*size(image,2));
paddedimage(1:size(image,1),1:size(image,2)) = image(1:size(image,1),1:size(image,2));%dhmiourgia padded image

%Convert image to 1 and -1
for i = 1 : size(paddedimage,1) 
    for j = 1 : size(paddedimage,2)
        multipliedimage(i,j) = ((-1)^(i+j).*paddedimage(i,j));
    end
end

%Shifted fast fourier transformation 2D
calculateFFTimage = fftshift(fft2(multipliedimage(:,:)));
%Diastance calculation
for u = 1 : size(paddedimage,1) 
    for v = 1 : size(paddedimage,1)
        D(u,v) = sqrt( ((u-(size(paddedimage,1)/2))^2) + ((v-(size(paddedimage,2)/2))^2) );
    end
end
%Calculation filter function
for u = 1 : size(calculateFFTimage,1)
    for v = 1 : size(calculateFFTimage,2)
        H(u,v) = 1 ./ (1 + ((D(u,v)./D0).^(2*n)));
    end
end

S = H(:,:) .* calculateFFTimage(:,:);
    
s = (real(ifft2(S(:,:))) .* multipliedimage(:,:));

%Cropping padding and getting final image
lastresult = s(1:(size(s,1)/2) , 1:(size(s,2)/2)); 

%Figures
figure;subplot(3,2,1);imshow(uint8(image));title('\color{green}Startin Image:');hold on;
subplot(3,2,2);imshow(uint8(paddedimage));title('\color{blue}Padded Image:');hold on;
subplot(3,2,3);imshow(uint8(multipliedimage));title('\color{magenta}Multiplied Image:');hold on;

subplot(3,2,4);imshow(H);title('\color[rgb]{1 0.4 0.6}Filter Imshow:');hold on;
subplot(3,2,5);mesh(H);title('\color{orange}Filter Mesh:');hold on;
subplot(3,2,6);imshow(uint8(lastresult));title('\color{cyan}Filtered Image:');hold off;

end

