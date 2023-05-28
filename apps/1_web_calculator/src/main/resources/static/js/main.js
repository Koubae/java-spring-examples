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

    /**
     *
     * @param endpoint {string}
     * @param payload {object}
     * @returns {Promise<string|*>}
     */
    async function send(endpoint, payload) {
        const url = `http://localhost:8080/api/v1/${endpoint}?` + new URLSearchParams(payload);

        try {
            const responseFetched = await fetch(url, {
                method: "POST",
            });
            const response = await responseFetched.json();
            apiResult.textContent = JSON.stringify(response);
            return response["result"];

        } catch (error) {
            console.error(error);
            apiResult.textContent = error;
            return "";
        }
    }

    buttonSubmitAddition.addEventListener("click", async _ => {
        const payload = {
            addendA: additionA.value,
            addendB: additionB.value,
        }

        let result = await send("add", payload);
        if (!result) {
            result = "ERROR";
        }
        additionResult.textContent = result;

    });


}());