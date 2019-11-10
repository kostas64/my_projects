function [a] = convert( path )

%πέρασμα αρχείου στον buffer 
v=VideoReader(path);
%διάβασμα αρχείου απο τον buffer
video = read(v);
%αναπαραγωγή video πριν την επεξεργασία
implay(video);
[rows,columns,~,frames] = size(video);
 a = zeros(rows,columns,frames);
 
 for i = 1:frames
     %Μετατροπή κάθε frame σε grayscale
     a(:,:,i) = rgb2gray(video(:,:,:,i));
 end
 %αναπαραγωγή μετά την μετατροπή σε grayscale
 implay(uint8(a));

end

