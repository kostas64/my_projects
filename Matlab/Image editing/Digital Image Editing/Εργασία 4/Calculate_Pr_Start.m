function Pr = Calculate_Pr_Start( image )
nk = zeros(1,256);
for i = 0 : 255
    nk(i+1) = length(find(image==i));
end  
    Pr = nk / ((size(image,1) * size(image,2)));
%figure;plot(0:255,Pr);xlabel('Bits');ylabel('Pr');
%subplot(2,1,2);plot(0:255,Prnew,'color',[0.8500, 0.3250, 0.0980]);xlabel('Bits');ylabel('PrNew');hold off;

end

