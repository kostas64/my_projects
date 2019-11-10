function new_image = Rgb2Hsi( image )
[h,w,d]= size(image);
imagebefore = zeros(h,w,d);

%--Tranform to HSI from RGB 
hsv_image = rgb2hsv(double(image));
%--Pr calculation for 3rd dimension
Pr = Calculate_Pr_Start(hsv_image(:,:,3));

%-- Balancing the values for all dimensions
for i = 1 : 3
imagebefore(:,:,i) =  Calculate_Sk(hsv_image(:,:,i),Pr);
end

%--Transform back to RGB
new_image = hsv2rgb(imagebefore);

end

