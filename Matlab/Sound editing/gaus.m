function [ z,y ] = gaus( x )

z=zeros(900000,2); %Δημιουργία πίνακα για
snr = 30;          %προσθεση θορυβου σε αυτον

z= awgn(z,snr); %Σήμα θορύβου
plot(z);

y = awgn(x,snr);    %Τελικό σήμα με θόρυβο Gaus

figure; subplot(6,1,1); plot(x(:,1)); subplot(6,1,2);plot(y(:,1));subplot(6,1,3); plot(x(:,2)); ...
subplot(6,1,4); plot(y(:,2)); subplot(6,1,5); plot(x); subplot(6,1,6); plot(y);
end

