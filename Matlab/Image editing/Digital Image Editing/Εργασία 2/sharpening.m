function  sharpening( image )
%-- 5x5 Leplacian filter
f1 = [1 1 1 1 1; 1 1 1 1 1; 1 1 -24 1 1; 1 1 1 1 1; 1 1 1 1 1];
f2 = [1 1 1; 1 -8 1; 1 1 1];
%--Convolution of filter & image
g1 = conv2(image,f1,'same');
g2 = conv2(image,f2,'same');
%--Image with edges
newimage = image - g2;
newimage2 = image - g1;
%----------------------------
%--Figure--------------------
figure; subplot(1,3,1); imshow(uint8(image));
subplot(1,3,2); imshow(uint8(newimage)); 
subplot(1,3,3); imshow(uint8(newimage2));
end

