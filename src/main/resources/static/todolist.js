/**
 * fetch 메소드로 요청을 보낼때 cstf token이 필요하여 미리 저장
 * 
 * @var {string}
 */
const csrfToken = document.querySelector('meta[name="_csrf"]').content;

/**
 * 체크박스 클릭시 완료 처리하기 위함
 *
 * @param {HTMLInputElement} checkbox checkbox element
 */
const handleCheckbox = (checkbox) => {
  if(checkbox.checked) {
      fetch("/user/todo/success/" + checkbox.value, {
          method: "POST",
          headers: {
              'X-CSRF-TOKEN': csrfToken //CSRF 토큰 추가
          },
      })
          .then(response => {
              if (!response.ok) {
                  throw new Error('Failed to update checkbox status');
              }
              return response.text();
          })
          .then(value => {
              if(value.trim() !== "ok") {
                  checkbox.checked = false;
              }
          })
          .catch(error => {
              console.error('Error', error);
              checkbox.checked = false;
          })
  } else {
      checkbox.checked = true; // 완료처리를 취소하지 못하도록 하기 위함
  }
}