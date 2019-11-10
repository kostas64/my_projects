function newImage = Calculate_Sk( Image, Pr)
newImage = Image;
Sk = zeros(1,256);

%--Computing of sum
for i = 0:255
    Sk(i+1) = 255*sum(Pr(1:i+1));
end
%--Rounding of sum
    Sk = round(Sk);    
%--Balancing image based on sum
 for i = 0:255
   newImage(Image==(i+1)) = Sk(i+1);
 end   
    
end

