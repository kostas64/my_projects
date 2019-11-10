function [ encoded,l ] = run_length_encoder( image )

[rows,columns] = size(image);
%μετρητης για πληθος επαναλήψεων, >3 για να υπαρχει οφελος κωδικοποίησης
counter=1;
%πίνακας κωδικοποίησης
encoded = [];
%αρχικό πλήθος bytes
b1=rows*columns; 

 for i = 1 : rows
     for j = 2 : columns 
         if image(i,j) == image(i,j-1)
             counter=counter+1;
           %αν τελείωσε η επαναλάψη των ίδιων τιμών και counter<3 (οχι όφελος)
          %βάλε στον πίνακα 1 ή 2 φορές την τιμή
         elseif image(i,j) ~= image(i,j-1) && counter < 3
                 
                 for k = 1 : counter
                      encoded = [encoded,image(i,j-1)];
                 end
                 counter=1;
          %αν τελείωσε η επαναλάψη των ίδιων τιμών και counter>3 (όφελος)
          %βάλε στον πίνακα το πλήθος, την τιμή και το σύμβολο -1 για
          %διαχωριστικό
         elseif image(i,j) ~= image(i,j-1) && counter >= 3
                 encoded = [encoded,counter];
                 encoded = [encoded,image(i,j-1)];
                 encoded = [encoded,-1];
                 counter=1;
         end
         %υλοποίηση αλγορίθμου για τα τελευταία κελιά κάθε σειράς 
         if j == columns && counter == 1 
              encoded = [encoded,image(i,j)];
         elseif j == columns && counter == 2
              encoded = [encoded,image(i,j)];
              encoded = [encoded,image(i,j)];
         elseif j == columns && counter > 2
              encoded = [encoded,counter];
              encoded = [encoded,image(i,j-1)];
              encoded = [encoded,-1];
         end
     end
     counter=1;
 end
 %υπολογισμός bytes για λόγο συμπίεσης
 [~,columns] = size(encoded);
 b2 = columns;
 l = b1/b2;
end

