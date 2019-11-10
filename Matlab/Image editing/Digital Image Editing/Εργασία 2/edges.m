function  edges( image,thresh_v)

magn = rgb2gray(image);
image2 = double(magn);

%--Computing the colvolution between Image & Sobel
%-- '-2' because skipping the outline
for i = 1 : size(image2,1) - 2
    for j = 1 : size(image2,2) - 2
        Gx=((image2(i+2,j)+2*image2(i+2,j+1)+image2(i+2,j+2))...
            -(image2(i,j)+2*image2(i,j+1)+image2(i,j+2)));
        Gy=((image2(i,j+2)+2*image2(i+1,j+2)+image2(i+2,j+2))...
            -(image2(i,j)+2*image2(i+1,j)+image2(i+2,j)));
        magn(i,j) = sqrt(double(Gx.^2+Gy.^2));
    end
end
figure('Name','Before Coloring'); imshow(magn);
%--Create new image------------
[h,w] = size(magn);
new = zeros(h,w,3);
%--RGB-------------------------
%--Coloring yellow the edges--- (1,2 dimension)
%--and blue the rest backgroung (3 dimension)
for i = 1 : h
    for j = 1 : w
        if (magn(i,j) < thresh_v)
            new(i,j,3) = 128;
        else
            new(i,j,1:2) = 255;
        end
    end
end

%------------------------------
%--Figures before & after edging
figure; subplot(1,2,1); imshow(image);
subplot(1,2,2); imshow(new);

end

