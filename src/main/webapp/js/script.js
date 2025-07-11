// ハンバーガーアイコンとメニュー本体の要素を取得
const hamburgerIcon = document.querySelector('.hamburger-icon');
const navLinks = document.querySelector('.nav-links');

// アイコンをクリックしたときの処理を設定
hamburgerIcon.addEventListener('click', () => {
    // ハンバーガーアイコンに"active"クラスを付けたり外したりする
    hamburgerIcon.classList.toggle('active');
    // メニュー本体にも"active"クラスを付けたり外したりする
    navLinks.classList.toggle('active');
});

/*
const loadingAreaGrey = document.querySelector('#loading');
const loadingAreaGreen = document.querySelector('#loading-screen');
const loadingAreaText = document.querySelector('#loading p');
window.addEventListener('load',() => {
	// ローディング中グレースクリーン
	loadingAreaGrey.animate(
		{
		opacity: [1,0],
		visibility:'hidden',
		},
	{
		duration: 2000,
		delay: 1200,
		easing:'ease',
		fill: 'forwards',
			}		
	);

// ローディング中(薄緑スクリーン）
	
	loadingAreaGreen.animate(
		{
			translae: ['0 100vh','0 0', '0 -100vh']
		},
		{duration: 2000,
		delay: 1200,
		easing:'ease',
		fill: 'forwards',
		}
	);
	
	// ローディング中テキスト
	loadingAreaText.animate(
		[
			{
				opacity: 1,
				offset: .8 //80%
				},
				{
					opacity:0,
					offset: 1 //100%
				},
			],
		{
			duration: 2000,
			delay: 1200,
			easing:'ease',
			fill: 'forwards',
			}
		);
	});
	
	*/