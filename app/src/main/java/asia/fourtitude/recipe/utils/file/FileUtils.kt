package asia.fourtitude.recipe.utils.file

import asia.fourtitude.recipe.data.model.recipe.RecipeType
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory

object FileUtils {

    fun parseRecipeTypesFromXml(xmlString: String): List<RecipeType> {
        val recipeTypes = mutableListOf<RecipeType>()

        val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val inputStream = ByteArrayInputStream(xmlString.toByteArray())
        val document = documentBuilder.parse(inputStream)

        val recipeTypeNodes = document.getElementsByTagName("recipetype")

        for (i in 0 until recipeTypeNodes.length) {
            val recipeTypeNode = recipeTypeNodes.item(i)

            if (recipeTypeNode.nodeType == Node.ELEMENT_NODE) {
                val element = recipeTypeNode as Element
                val id = element.getElementsByTagName("id").item(0).textContent
                val name = element.getElementsByTagName("name").item(0).textContent
                val recipeType = RecipeType(id, name)
                recipeTypes.add(recipeType)
            }
        }

        return recipeTypes
    }

}