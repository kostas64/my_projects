function print3( image )
Pr = zeros(1,256);
%--Calculate Pr for diagramms
for i = 1 : 3
Pr = Calculate_Pr_Start(image(:,:,i));
end
%--Image Transformation
new_image = Rgb2Hsi(image);

%--Calculate Pr for diagramms
Prnew = Calculate_Pr_Start(new_image);

%-----------------------------
%--Figures--------------------
figure;subplot(2,2,1);imshow(uint8(image));title('Image Before');hold on;
subplot(2,2,2);imshow(uint8(new_image));title('Image After');hold on;
subplot(2,2,3);plot(0:255,Pr);xlabel('Range');ylabel('Pr');hold on;
subplot(2,2,4);plot(0:255,Prnew,'color',[0.8500, 0.3250, 0.0980]);xlabel('Range');ylabel('PrNew');hold off;


end

