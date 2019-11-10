function  salt_pepper( image )

[h,w] = size(image);
rand_n = zeros(h,w);
salt_pepper = image;
newImage_med = image;

for i = 1 : h
    for j = 1 : w
        %--Generating randomness
        rand_n(i,j) = rand(1);
        %--10% black noise
        if rand_n(i,j) <= 0.1    
            salt_pepper(i,j) = 0;
        %--10% white noise
        elseif rand_n(i,j) >=0.9 
            salt_pepper(i,j) = 255;
        end
    end
end


%--Average filter 3x3
filter_avg = [1/9 1/9 1/9; 1/9 1/9 1/9; 1/9 1/9 1/9];
%--Computing the convolution 
newImage_avg = conv2(salt_pepper,filter_avg,'same');

%--Replacing image value with 
%--the average one----------- 
for i = 2 : size(image,1) - 2
    for j = 2 : size(image,2) - 2
        A = newImage_med(i-1:i+1,j-1:j+1);
        %--Convert the middle of each 
        %--neighborhood after sorting
        B = median(sort(A(:)));
        newImage_med(i,j) = B;
    end
end

%---------------------------
%--Figures------------------
figure;
subplot(1,3,1); imshow(uint8(image));        title('\color{green}Startin Image:');hold on;
subplot(1,3,2); imshow(uint8(salt_pepper));  title('\color{red}Image After Noise:');hold on;
subplot(1,3,3); imshow(uint8(newImage_avg)); title('\color{magenta}Image After Mean Filter:');hold on;
%---------------------------
figure;
subplot(1,3,1); imshow(uint8(image));        title('\color{green}Startin Image:');hold on;
subplot(1,3,2); imshow(uint8(salt_pepper));  title('\color{red}Image After Noise:');hold on;
subplot(1,3,3); imshow(uint8(newImage_med)); title('\color{magenta}Image After Median Filter:');hold on;


end

