function print(Mer,x_cl,x_fl)
fprintf('Το εύρος τιμών της εικόνας Merilin είναι: %d kai %d\n',min(Mer(:)),max(Mer(:)));
fprintf('Το εύρος τιμών της εικόνας flowers είναι: %d kai %d\n',min(x_fl(:)),max(x_fl(:)));
fprintf('Το εύρος τιμών της εικόνας clock είναι: %d kai %d\n',min(x_cl(:)),max(x_cl(:)));
mer = Balance(Mer);
clo = Balance(x_cl);
flo = Balance(x_fl);
%Figures
figure;subplot(3,2,1);imshow(uint8(Mer));subplot(3,2,2);imshow(uint8(mer));hold on;
subplot(3,2,3);imshow(uint8(x_cl));subplot(3,2,4);imshow(uint8(clo));hold on;
subplot(3,2,5);imshow(uint8(x_fl));subplot(3,2,6);imshow(uint8(flo));hold on;
end

