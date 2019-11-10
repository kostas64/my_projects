function FirstExercise( image )
a = 0:255;
Pr = Calculate_Pr_Start(image);
%Calculation of entropy
H = - sum(Pr(Pr(:)>0) .* log2(Pr(Pr(:)>0)));
%Huffman dictionary 
[dict,avglen] = huffmandict(a(Pr(:) > 0),Pr(Pr(:)>0));
%Huffman encoding
encoding = huffmanenco(image(:),dict);
%Computation of efficiency with use of entropy
h = H / avglen;
%Computation of compression with use of average length of coding word
C = 8 / avglen;
%Validation of results
C2 = ((size(image,1) * size(image,2))*8 ) / length(encoding);
R = 1 - (1/C);
fprintf('Entropy(H) : %f\nEffiency(h) : %f\nCompression Ratio(C) : %f\nPleonasm of Image(R) : %f (%.2f%%)',H,h,C,R,R*100);


end

