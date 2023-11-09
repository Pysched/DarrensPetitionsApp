document.addEventListener('DOMContentLoaded', function () {
    var searchInput = document.getElementById("searchInput");
    var searchResults = document.getElementById("searchResults");

    searchInput.addEventListener("input", function () {
        var searchTerm = this.value.trim(); // Remove leading/trailing white spaces

        // Make an AJAX request to the endpoint with the lowercase search term
        fetch(`/search/${searchTerm.toLowerCase()}`)
            .then(response => response.json())
            .then(data => {
                searchResults.innerHTML = "";

                data.forEach(function (petition) {
                    var listItem = document.createElement("a");
                    listItem.className = "list-group-item list-group-item-action";
                    listItem.href = "/result/" + petition.petitionTitle;
                    listItem.innerHTML = petition.petitionTitle;

                    searchResults.appendChild(listItem);
                });
            });
    });
});
