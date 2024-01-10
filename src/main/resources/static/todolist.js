/**
 *
 * @param {HTMLInputElement} checkbox checkbox element
 */
const handleCheckbox = (checkbox) => {
  if(checkbox.checked) {
      fetch("/user/todo/success/" + checkbox.value, {method: "POST"})
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
      checkbox.checked = true;
  }
}