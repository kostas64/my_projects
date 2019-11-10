function Pr = Calculate_Pr_Start( image )
nk = zeros(1,256);
for i = 0 : 255
    nk(i+1) = length(find(image==i));
end  
    Pr = nk / ((size(image,1) * size(image,2)));


end

