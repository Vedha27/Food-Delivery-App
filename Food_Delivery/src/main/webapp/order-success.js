// 🎊 Fire confetti
 const duration = 4 * 1000;
 const animationEnd = Date.now() + duration;
 const defaults = { startVelocity: 30, spread: 360, ticks: 60, zIndex: 1000 };

 function randomInRange(min, max) {
   return Math.random() * (max - min) + min;
 }

 const interval = setInterval(function () {
   const timeLeft = animationEnd - Date.now();

   if (timeLeft <= 0) {
     return clearInterval(interval);
   }

   confetti(Object.assign({}, defaults, {
     particleCount: 50,
     origin: { x: randomInRange(0.1, 0.9), y: Math.random() - 0.2 }
   }));
 },250);