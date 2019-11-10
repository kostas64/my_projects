function [ decoded ] = decoder( encoded,dimen )

%αρχικοποίηση πίνακα αποκωδικοποίησης, indexes
decoded = zeros(dimen,dimen);
[~,columns] = size(encoded);
i=1;
j=1;
k=1;

    while 1
        %έλεγχος για εύρεση συμβόλου -1
        if encoded(i+2)== -1
            %βάλε για όσο πλήθος βρήκες στον encoded ,τώσα κελιά
            for l = k:k+encoded(i) - 1
                decoded(j,l) = encoded(i+1);
            end
            %έλεγχος για αλλαγή γραμμής και παραμετροποίηση indexes
            if l==dimen
                k=1;
                j=j+1;
            else
                k=k+encoded(i);
            end
            i=i+3;
           %αν δεν βρήκες -1 στο +2 κελί
        else
            %έλεγξε αν χρειάζεται να αλλάξεις γραμμή
            %και παραμετροποίηση εκ νέου τους indexes
            if k==dimen
                 decoded(j,k) = encoded(i);
                k=1;
                j=j+1;
               
                i=i+1;
            else
                %αλλιώς απλά πρόσθεσε το στοιχείο
                %και προχώρα τους indexes
                decoded(j,k) = encoded(i);
                 i=i+1;
                 k=k + 1;
            end
            
        end
        %έλεγχος για το αν έφτασε ο encoded στο τέλος
        if i + 2 == columns
            break;
        end
    
    end
    %υλοποίηση αλγορίθμου για τα τελευταία 3 κελιά
   if encoded(i+2) == -1
       for l = k : k + encoded(i) - 1
           decoded(j,l) = encoded(i+1);
       end
   else
       decoded(j,k) = encoded(i);
       k = k + 1;
       i = i + 1;
       decoded(j,k) = encoded(i);
       k = k + 1;
       i = i + 1;
       decoded(j,k) = encoded(i);
   end

end

