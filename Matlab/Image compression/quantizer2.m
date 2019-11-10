function [frames] =  quantizer2(image)
    
    %κβάντιση εικόνας για διαφορετικές τιμές bits/pixel
    out1 = quantizer(image,1);
    out2 = quantizer(image,2);
    out3 = quantizer(image,3);
    out4 = quantizer(image,4);
    out5 = quantizer(image,5);
    out6 = quantizer(image,6);
    out7 = quantizer(image,7);
    out8 = quantizer(image,8);
        %Συγχώνευση κβαντισμένων εικόνων σε 1 plot
        subplot(2,4,1); imshow(out1); subplot(2,4,2); imshow(out2);...
        subplot(2,4,3); imshow(out3); subplot(2,4,4); imshow(out4);...
        subplot(2,4,5); imshow(out5); subplot(2,4,6); imshow(out6);...
        subplot(2,4,7); imshow(out7); subplot(2,4,8); imshow(out8);
       
    %RLE για όλες τις κβαντισμένες εικόνες
    [~,l1] = run_length_encoder(out1);
    [~,l2] = run_length_encoder(out2);
    [~,l3] = run_length_encoder(out3);
    [~,l4] = run_length_encoder(out4);
    [~,l5] = run_length_encoder(out5);
    [~,l6] = run_length_encoder(out6);
    [~,l7] = run_length_encoder(out7);
    [~,l8] = run_length_encoder(out8);
    
    %λόγοι συμπίεσης και figure αυτών
    l = [l1,l2,l3,l4,l5,l6,l7,l8];
    figure; plot(l);
    
    %δημιουργία colormap ,λόγω grayscale
    map = colormap(gray(256));
    
    %παίρνω frames για το movie
    f1 = im2frame(out1,map);
    f2 = im2frame(out2,map);
    f3 = im2frame(out3,map);
    f4 = im2frame(out4,map);
    f5 = im2frame(out5,map);
    f6 = im2frame(out6,map);
    f7 = im2frame(out7,map);
    f8 = im2frame(out8,map);
    %επιστρέφω πίνακα με frames
    frames = [f1,f2,f3,f4,f5,f6,f7,f8];

    

end

