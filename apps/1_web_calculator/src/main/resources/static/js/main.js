(function main() {
    const additionA = document.getElementById("jsAddA");
    const additionB = document.getElementById("jsAddB");
    const additionResult = document.getElementById("jsAddResult");
    const buttonSubmitAddition = document.getElementById("jsSubmitAddition");

    const subtractionA = document.getElementById("jsSubtractionA");
    const subtractionB = document.getElementById("jsSubtractionB");
    const subtractionResult = document.getElementById("jsSubtractionResult");
    const buttonSubmitSubtraction = document.getElementById("jsSubmitSubtraction");

    const multiplicationA = document.getElementById("jsMultiplierA");
    const multiplicationB = document.getElementById("jsMultiplierB");
    const multiplicationResult = document.getElementById("jsMultiplierResult");
    const buttonSubmitMultiplication = document.getElementById("jsSubmitMultiplication");

    const divisionA = document.getElementById("jsDivisionA");
    const divisionB = document.getElementById("jsDivisionB");
    const divisionResult = document.getElementById("jsDivisionResult");
    const buttonSubmitDivision = document.getElementById("jsSubmitDivision");

    const apiResult = document.getElementById("jsApiResult");

    buttonSubmitAddition.addEventListener("click", async e => {
        const a = additionA.value;
        const b = additionB.value;

        const url = "http://localhost:8080/api/v1/add?" + new URLSearchParams({
            addendA: a,
            addendB: b,
        });

       try {
           const responseFetched = await fetch(url, {
               method: "POST",
           });
           const response = await responseFetched.json();
           additionResult.textContent = response["result"];
           apiResult.textContent = JSON.stringify(response);

       } catch (error) {
           console.error(error);
           apiResult.textContent = error;
       }

    });


}());