function [ c ] = filter_avg( image )

a = fspecial ('average'); %φιλτρο 3χ3 μεσου ορου
c = conv2(image,a,'same'); %συνέλιξη εικόνας και φίλτρου
imshow(c);

end

