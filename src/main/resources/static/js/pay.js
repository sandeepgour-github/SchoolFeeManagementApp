document.getElementById("payForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const studentId = document.getElementById("studentId").value.trim();
  const amount = parseFloat(document.getElementById("amount").value.trim());

  const token = localStorage.getItem("token");
  if (!token) {
    Swal.fire(
      "Unauthorized",
      "You must be logged in to make a payment.",
      "error"
    );
    return;
  }

  fetch(`http://localhost:8081/api/fees/${studentId}?amount=${amount}`, {
    method: "POST",
    headers: {
      Authorization: "Bearer " + token, // ✅ Token header add
    },
  })
    .then((res) => {
      if (!res.ok) {
        return res.json().then((errorData) => {
          throw new Error(errorData.message || "Unknown error");
        });
      }
      return res.json();
    })
    .then((data) => {
      Swal.fire("Success", "Payment recorded!", "success");

      const receiptURL = `http://localhost:8081/api/admin/receipts/${studentId}_${data.id}.pdf`;

      // Create or update download button
      let existingBtn = document.getElementById("downloadReceiptBtn");

      if (!existingBtn) {
        existingBtn = document.createElement("a");
        existingBtn.id = "downloadReceiptBtn";
        existingBtn.className = "btn btn-success mt-3";
        existingBtn.target = "_blank";
        document.getElementById("payForm").after(existingBtn);
      }

      existingBtn.href = receiptURL;
      existingBtn.textContent = "Download Latest Receipt";
    })
    .catch((err) => {
      Swal.fire("Error", err.message, "error");
    });
});
