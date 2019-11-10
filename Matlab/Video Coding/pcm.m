function [ l ] = pcm( a )
    %αρχικοποιηση πινακων
 [rows,columns,frames] = size(a);
  b = zeros(rows,columns,frames);
  c = zeros(rows,columns);
  %περασμα 1ου frame στον καινουριο πινακα
  b(:,:,1) = a(:,:,1);
 
  
 for i = 2 : frames 
     %υπολογισμός διαφορών μεταξύ διαδοχικων πλαισιων
     b(:,:,i) = a(:,:,i) - a(:,:,i-1);
 end
     

 for i = 1 : rows
     for j = 1 : columns
         %υπολογισμος bits  κβαντισμού για τις διαφορές/pixel
         c(i,j) = ceil(log2((max(b(i,j,2:frames)) - min(b(i,j,2:frames)) + 1)));
     end
 end
 %λόγος συμπιεσης
 l = (rows*columns*8*frames) / (rows*columns*8 + (frames-1)*sum(c(:)));

end

