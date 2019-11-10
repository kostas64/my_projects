function newImage = hist_eq(image)
%--Instatiation
[h,w,d] = size(image);
pix = h*w*d;
newImage = image;
Sk = zeros(1,256);
nk = zeros(1,256);
nk2 = zeros(1,256);
L = 256;

%--Multitude of numbers & Probability of occurence
for i = 0 : L-1
    nk(i+1) = length(find(image==i));
end  
    Pr = nk / pix;

%--Transformation 
for i = 0:L-1
    Sk(i+1) = (L-1)*sum(Pr(1:i+1));
end

 Sk = round(Sk); 

for i = 0:L-1
    newImage(image==i+1) = Sk(i+1);
end

%--NEW values
for i = 0 : L-1
    nk2(i+1) = length(find(newImage==i));
end  
    Pr2 = nk2 / pix;

%----------------------------------
%--Figure of Images----------------
    figure; subplot(1,2,1); imshow(uint8(image));
    subplot(1,2,2); imshow(uint8(newImage));
%----------------------------------
%--Figure of Histograms-------------
    figure('Name','Histograms','NumberTitle','off');  
    subplot(2,1,1); plot(0:255,Pr);
    subplot(2,1,2); plot(0:255,Pr2); 



end

