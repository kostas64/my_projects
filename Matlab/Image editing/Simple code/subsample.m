function [image] = subsample( image )
newImage = image(1:16:end,1:16:end,:); %Υποδειγματολειπτούμε κατα 16
    imshow(newImage);                  %Τελικο μέγεθος εικονας 400/16 Χ 400/16
end

