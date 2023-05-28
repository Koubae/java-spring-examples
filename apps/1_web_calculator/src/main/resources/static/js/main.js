(function main() {
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

    (function initAddition() {
        const additionA = document.getElementById("jsAddA");
        const additionB = document.getElementById("jsAddB");
        const additionResult = document.getElementById("jsAddResult");
        const buttonSubmitAddition = document.getElementById("jsSubmitAddition");

        if (!additionA || !additionB || !additionResult || !buttonSubmitAddition) {
            const errorMessage = "Could not initialize Calculator Addition!";
            console.error(errorMessage);
            alert(errorMessage);
        }

        buttonSubmitAddition.addEventListener("click", async _ => {
            const payload = {
                valueA: additionA.value,
                valueB: additionB.value,
            }

            let result = await send("add", payload);
            if (!result) {
                result = "ERROR";
            }
            additionResult.textContent = result;
        });

    }());

    (function initSubtraction() {
        const subtractionA = document.getElementById("jsSubtractionA");
        const subtractionB = document.getElementById("jsSubtractionB");
        const subtractionResult = document.getElementById("jsSubtractionResult");
        const buttonSubmitSubtraction = document.getElementById("jsSubmitSubtraction");

        if (!subtractionA || !subtractionB || !subtractionResult || !buttonSubmitSubtraction) {
            const errorMessage = "Could not initialize Calculator Subtraction!";
            console.error(errorMessage);
            alert(errorMessage);
        }

        buttonSubmitSubtraction.addEventListener("click", async _ => {
            const payload = {
                valueA: subtractionA.value,
                valueB: subtractionB.value,
            }

            let result = await send("subtract", payload);
            if (!result) {
                result = "ERROR";
            }
            subtractionResult.textContent = result;
        });

    }());

    (function initMultiplication() {
        const multiplicationA = document.getElementById("jsMultiplierA");
        const multiplicationB = document.getElementById("jsMultiplierB");
        const multiplicationResult = document.getElementById("jsMultiplierResult");
        const buttonSubmitMultiplication = document.getElementById("jsSubmitMultiplication");

        if (!multiplicationA || !multiplicationB || !multiplicationResult || !buttonSubmitMultiplication) {
            const errorMessage = "Could not initialize Calculator Multiplication!";
            console.error(errorMessage);
            alert(errorMessage);
        }

        buttonSubmitMultiplication.addEventListener("click", async _ => {
            const payload = {
                valueA: multiplicationA.value,
                valueB: multiplicationB.value,
            }

            let result = await send("multiply", payload);
            if (!result) {
                result = "ERROR";
            }
            multiplicationResult.textContent = result;
        });

    }());

    (function initDivision() {
        const divisionA = document.getElementById("jsDivisionA");
        const divisionB = document.getElementById("jsDivisionB");
        const divisionResult = document.getElementById("jsDivisionResult");
        const buttonSubmitDivision = document.getElementById("jsSubmitDivision");

        if (!divisionA || !divisionB || !divisionResult || !buttonSubmitDivision) {
            const errorMessage = "Could not initialize Calculator Division!";
            console.error(errorMessage);
            alert(errorMessage);
        }

        buttonSubmitDivision.addEventListener("click", async _ => {
            const payload = {
                valueA: divisionA.value,
                valueB: divisionB.value,
            }

            let result = await send("divide", payload);
            if (!result) {
                result = "ERROR";
            }
            divisionResult.textContent = result;
        });

    }());


}());