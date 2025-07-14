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
        return response.text();
      })
      .then(data => {
        const button = form.querySelector('.buzz-button');
        const countSpan = form.querySelector('.buzz-count');
        let current = parseInt(countSpan.textContent);

        if (button.classList.contains('buzzed')) {
          button.classList.remove('buzzed');
          button.textContent = 'バズる🔥';
          countSpan.textContent = current - 1;
        } else {
          button.classList.add('buzzed');
          button.textContent = 'バズ済み✔️';
          countSpan.textContent = current + 1;
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