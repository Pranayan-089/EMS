const apiUrl = "http://localhost:8080";

// Fetch and display all employees
function fetchEmployees() {
  fetch(`${apiUrl}/emps`)
    .then((response) => response.json())
    .then((data) => {
      // Sort employees by id in ascending order
      data.sort((a, b) => a.id - b.id);
      const tableBody = document.getElementById("employeeTableBody");
      tableBody.innerHTML = "";
      data.forEach((employee) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.phone}</td>
                    <td>${employee.email}</td>
                    <td class="actions">
                        <button class="edit" onclick="editEmployee(${employee.id})">Edit</button>
                        <button class="delete" onclick="deleteEmployee(${employee.id})">Delete</button>
                    </td>
                `;
        tableBody.appendChild(row);
      });
    });
}

// Add or update employee
document
  .getElementById("employeeForm")
  .addEventListener("submit", function (e) {
    e.preventDefault();
    const id = document.getElementById("employeeId").value;
    const name = document.getElementById("name").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;

    const employee = { name, phone, email };

    const method = id ? "PUT" : "POST";
    const url = id ? `${apiUrl}/emp/${id}` : `${apiUrl}/emp`;

    fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(employee),
    }).then(() => {
      fetchEmployees();
      document.getElementById("employeeForm").reset();
    });
  });

// Edit employee
function editEmployee(id) {
  fetch(`${apiUrl}/emp/${id}`)
    .then((response) => response.json())
    .then((employee) => {
      document.getElementById("employeeId").value = employee.id;
      document.getElementById("name").value = employee.name;
      document.getElementById("phone").value = employee.phone;
      document.getElementById("email").value = employee.email;
    });
}

// Delete employee
function deleteEmployee(id) {
  fetch(`${apiUrl}/emp/${id}`, { method: "DELETE" }).then(() =>
    fetchEmployees()
  );
}

// Search employees
document.getElementById("searchButton").addEventListener("click", function () {
  const keyword = document.getElementById("searchKeyword").value;
  fetch(`${apiUrl}/emp/search/${keyword}`)
    .then((response) => response.json())
    .then((data) => {
      // Sort employees by id in ascending order
      data.sort((a, b) => a.id - b.id);
      const tableBody = document.getElementById("employeeTableBody");
      tableBody.innerHTML = "";
      data.forEach((employee) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.phone}</td>
                    <td>${employee.email}</td>
                    <td class="actions">
                        <button class="edit" onclick="editEmployee(${employee.id})">Edit</button>
                        <button class="delete" onclick="deleteEmployee(${employee.id})">Delete</button>
                    </td>
                `;
        tableBody.appendChild(row);
      });
    });
});

// Initial fetch
fetchEmployees();
