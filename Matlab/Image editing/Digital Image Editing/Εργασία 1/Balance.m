function after_b = Balance( image )
im_min = min(image(:));
im_max = max(image(:));
[w1,w2] = calculate(im_min,im_max);
after_b = image(:,:) .* w1 + w2;


end

