function [ w1,w2 ] = calculate( im_min,im_max )
%Calculating weights 
w1 = double(255 / (im_max - im_min));
w2 = double(255 - (im_max * w1));


end

