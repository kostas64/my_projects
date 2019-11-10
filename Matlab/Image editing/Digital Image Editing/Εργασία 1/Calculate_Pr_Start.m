function Pr = Calculate_Pr_Start( image )
nk = zeros(1,256);

%--Multitude of numbers 
for i = 0 : 255
    nk(i+1) = length(find(image==i));
end  

%--Probability of occurence
    Pr = nk / ((size(image,1) * size(image,2)));
   
end

