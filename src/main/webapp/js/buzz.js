document.addEventListener('DOMContentLoaded', function () {
  const buzzForms = document.querySelectorAll('.buzz-form');

  buzzForms.forEach(function (form) {
    form.addEventListener('submit', function (e) {
      e.preventDefault(); // ページ遷移を防ぐ

      const formData = new FormData(form);

      fetch('BuzzServlet', {
        method: 'POST',
        body: formData
      })
      .then(response => {
        if (!response.ok) throw new Error('通信失敗');
        return response.json();
      })
      .then(data => {
        const button = form.querySelector('.buzz-button');
        const countSpan = form.querySelector('.buzz-count');
        //let current = parseInt(countSpan.textContent);
		
		//buzzカウントにサーブレットから受け取る値を確認
		countSpan.textContent = data.buzzCount;
		// data.likedがtrueなら、バズ済み状態にする
		if (data.liked) {
			button.classList.add('buzzed'); // クラスを追加
			button.textContent = 'バズ済み✔️';
		} else { // data.likedがfalseなら、まだバズってない状態に戻す
			button.classList.remove('buzzed'); // クラスを削除
			button.textContent = 'バズる🔥';
			}
		})
      .catch(error => {
        console.error('バズエラー:', error);
        alert('バズに失敗しました');
      });
    });
  });
});
/**
 * 
 */