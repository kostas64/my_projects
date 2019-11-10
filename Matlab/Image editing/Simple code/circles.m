function  [image] = circles( image , N , M )
d = zeros(400,400); %Πινακας αποστασεων για ολα τα pixel
a = 200/(M+N);      %πληθος κυκλων 

     for i=1:a  
       for x = 1 : 400
         for y = 1 : 400
             z = (x-200).^2;
             c = (y-200).^2;
             
                 d (x,y) = sqrt( z + c ); %υπολογισμος ευκλειδιας αποσταστης
             
                 if  d(x,y) > (i-1)*(N+M)+N  &&  d(x,y)<= (i-1)*(N+M)+N+M %ελεγχος αποστασης pixel για βαψιμο ή μη
                     image(x,y) = 0;    %βαψε μαυρο
                     drawnow;
                end
         end
       end
     end
     
    imshow(image); %Εμφανιση εικονας
end

