function ForthExe( image , image2)
Pr = Calculate_Pr_Start(image);
Pr2 = Calculate_Pr_Start(image2);

%Selection of part of image
imageSlice1 = image(250:300,250:300); 
imageSlice2 = image2(250:300,250:300);

PrSlice1 = Calculate_Pr_Start(imageSlice1);
PrSlice2 = Calculate_Pr_Start(imageSlice2);


Z1 = zeros(256,1);
Z2 = zeros(256,1);
S1 = zeros(256,1);
S2 = zeros(256,1);
%Mean value
for i = 1  : 256 
    Z1(i) = sum(PrSlice1(i) * i);
    Z2(i) = sum(PrSlice2(i) * i);
end
%Dispression calculation    
for i = 1  : 256 
    S1(i) = sum(((i-Z1(i))^2) * PrSlice1(i));
    S2(i) = sum(((i-Z2(i))^2) * PrSlice2(i));
end
%Figures
figure('units','normalized','outerposition',[0 0 1 1]);get(0,'Factory');set(0,'defaultfigurecolor',[0 0 0]);get(groot, 'Factory');set(groot,{'DefaultAxesXColor','DefaultAxesYColor','DefaultAxesZColor'},{'w','w','w'})
subplot(2,2,1);imshow(uint8(image));title('\color{green}First Image (Gaussian):');hold on;
subplot(2,2,2);imshow(uint8(image2));title('\color{green}Second Image (Salt & Pepper):');hold on;
subplot(2,2,3);plot(0:255,Pr);xlabel('Bits');ylabel('Pr');title('\color{black}Padded Image:');hold on;
subplot(2,2,4);plot(0:255,Pr2);xlabel('Bits');ylabel('Pr');title('\color{black}Padded Image:');hold off;
fprintf('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nNoisy Image 1: Gaussian noise \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nNoisy Image 2: Salt and Pepper noise\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n');
fprintf('Noisy Image 1: Mean value = %f \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nNoisy Image 1: Dispression = %f \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nNoisy Image 2: Mean Value = %f \n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nNoisy Image 2: Dispression = %f\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n',sum(Z1),sum(S1),sum(Z2),sum(S2));
end

