function [out_image] = quantizer(image,n)

  z=power(2,n); % # τμημάτων των συνιστωσών
  k = 256;      %συνιστώσες
  euros = k/z;  %εύρος
  
 [rows,columns] = size(image);
  out_image = zeros(rows,columns,'uint8');
  
    for i = 1 : rows
        for j = 1 : columns
            
            x = image(i,j)/euros;
            out_image(i,j) = euros * round(x);
            %στρογγυλοποίηση στον πρώτο πάνω ακέραιο
        end
    end
    

end

