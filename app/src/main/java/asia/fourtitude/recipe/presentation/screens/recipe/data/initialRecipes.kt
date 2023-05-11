package asia.fourtitude.recipe.presentation.screens.recipe.data

import asia.fourtitude.recipe.data.model.recipe.Recipe
import asia.fourtitude.recipe.data.model.recipe.RecipeCategory
import asia.fourtitude.recipe.data.model.recipe.RecipeType

fun getInitialRecipes() = mutableListOf(
                    Recipe(
                        name = "Simple Lemon-Herb Chicken",
                        featuredImageUrl = "https://images.unsplash.com/photo-1574894709920-11b28e7367e3?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHwxfHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw\\u0026ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.BEEF.id,
                                name = RecipeCategory.BEEF.name,
                            )
                        ),
                        description = "Lemon-herb chicken is a simple, quick, and delicious dish. All you need are a few herbs, a lemon, and of course, the chicken! The amount of spices are completely up to you. You can add more or less according to your taste.",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "2 (5 ounce) skinless, boneless chicken breast halves",
                            "1 medium lemon, juiced, divided",
                            "salt to taste",
                            "1 tablespoon olive oil",
                            "1 pinch dried oregano",
                            "ground black pepper to taste",
                            "2 sprigs fresh parsley, for garnish",
                        ),
                        steps = mutableListOf(
                            "Place chicken in a bowl, pour 1/2 of the lemon juice over top and season with salt.",
                            "Heat oil in a small skillet over medium-low heat. Add chicken to the hot oil, along with the remaining lemon juice and oregano. Season with pepper. Sauté until golden brown and the juices run clear, 5 to 10 minutes per side.",
                            "Serve with parsley for garnish.",
                        )
                    ),
                    Recipe(
                        name = "One Pan Orecchiette Pasta",
                        featuredImageUrl = "https://images.unsplash.com/photo-1619895092538-128341789043?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHwyfHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw&ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.CHICKEN.id,
                                name = RecipeCategory.CHICKEN.name,
                            )
                        ),
                        description = "This delicious orecchiette pasta recipe only has a handful of ingredients, is very cheap to make, and most importantly, only uses one pan or pot for the entire procedure. Every year when it's time to go back to school I get inundated with requests from students to post recipes that are super-easy, cost pennies, and require a bare minimum of kitchen equipment — this is one of them!",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "2 tablespoons olive oil",
                            "½ onion, diced",
                            "salt to taste",
                            "8 ounces spicy Italian sausages, casings removed",
                            "3 ½ cups low-sodium chicken broth, divided, or as needed",
                            "1 ¼ cups orecchiette pasta, or more to taste",
                            "½ cup roughly chopped arugula, or to taste",
                            "¼ cup finely grated Parmigiano-Reggiano cheese, or to taste",
                        ),
                        steps = mutableListOf(
                            "Heat olive oil in a large, deep skillet over medium heat. Add onion with a pinch of salt; cook and stir until onion has softened and turned translucent, about 5 minutes. Stir in sausage and cook until browned, 5 to 7 minutes.",
                            "Pour 1 1/2 cups chicken broth into sausage mixture and bring to a boil while scraping the browned bits of food off of the bottom of the pan with a wooden spoon. Add orecchiette pasta; cook and stir pasta in hot broth, adding remaining broth when liquid is absorbed, until pasta is cooked through and most of the broth is absorbed, about 15 minutes.",
                            "Stir arugula into sausage-pasta mixture until arugula wilts. Ladle pasta into bowls and sprinkle with Parmigiano-Reggiano cheese.",
                        )
                    ),
                    Recipe(
                        name = "Sloppy Joe Casserole",
                        featuredImageUrl = "https://images.unsplash.com/photo-1619894991209-9f9694be045a?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHwzfHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw&ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.DESSERT.id,
                                name = RecipeCategory.DESSERT.name,
                            )
                        ),
                        description = "This is a great comfort food recipe that I love to make on a cold winter day. It is also a hit at our church's potluck dinners.",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "1 pound ground beef",
                            "1 small onion, chopped",
                            "1 green bell pepper, chopped",
                            "1 red bell pepper, chopped",
                            "1 clove garlic, chopped",
                            "1 (14.5 ounce) can petite diced tomatoes",
                            "1 cup ketchup",
                            "1 tablespoon brown sugar",
                            "1 teaspoon yellow mustard",
                            "salt and ground black pepper to taste",
                            "2 cups frozen whole-kernel corn",
                            "1 (16 ounce) package penne pasta",
                            "1 ½ cups shredded Colby-Jack cheese",
                        ),
                        steps = mutableListOf(
                            "Preheat oven to 350 degrees F (175 degrees C).",
                            "Heat a large skillet over medium-high heat. Cook and stir beef, onion, green bell pepper, red bell pepper, and garlic in the hot skillet until beef is browned and crumbly, 5 to 7 minutes; drain and discard grease.",
                            "Stir tomatoes, ketchup, brown sugar, mustard, salt, and pepper into ground beef mixture; reduce heat and simmer until heated through, about 10 minutes.",
                            "Bring a large pot of lightly salted water to a boil. Cook corn in the boiling water until cooked through, about 5 minutes; drain.",
                            "Bring a large pot of lightly salted water to a boil; add penne and cook, stirring occasionally, until tender yet firm to the bite, about 11 minutes. Drain.",
                            "Mix corn, pasta, and Colby-Jack cheese into ground beef mixture; pour into a 9x13-inch baking dish.",
                            "Bake in the preheated oven until heated through and cheese is melted, about 20 minutes.",
                        )
                    ),
                    Recipe(
                        name = "Beef Enchiladas II",
                        featuredImageUrl = "https://images.unsplash.com/photo-1597289124948-688c1a35cb48?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHw0fHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw&ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.LAMB.id,
                                name = RecipeCategory.LAMB.name,
                            )
                        ),
                        description = "A simple, quick, easy beef enchiladas recipe. Ground beef and onion are wrapped in flour tortillas, topped with Cheddar cheese and black olives, then baked. This is also great with leftover chicken, shredded beef, or turkey. Serve with a green salad or beans and rice.",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "1 pound lean ground beef",
                            "1 small onion, chopped",
                            "1 (1.5 ounce) package dry enchilada sauce mix",
                            "10 (10 inch) flour tortillas",
                            "2 cups shredded Cheddar cheese, divided",
                            "1 (2.25 ounce) can sliced black olives, drained",
                        ),
                        steps = mutableListOf(
                            "Preheat the oven to 350 degrees F (175 degrees C).",
                            "Cook ground beef and onion in a medium skillet over medium-high heat until beef is evenly browned and onion is tender.",
                            "Prepare enchilada sauce according to package directions. Pour 1/4 cup of the sauce into the bottom of a 9x13-inch baking dish.",
                            "Place an equal portion of the ground beef mixture and about 1 ounce of Cheddar cheese on each flour tortilla, reserving at least 1/2 cup of cheese. Then tightly roll the tortillas.",
                            "Place rolled tortillas seam side down in the baking dish.",
                            "Pour remaining sauce over the top of the enchiladas and sprinkle with remaining cheese and olives.",
                            "Bake in the preheated oven until heated through and cheese is melted, about 20 minutes.",
                            "Garnish to taste and serve with your favorite side. Enjoy!",
                        )
                    ),
                    Recipe(
                        name = "Tuna Lime Tostadas",
                        featuredImageUrl = "https://images.unsplash.com/photo-1629115916087-7e8c114a24ed?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHw1fHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw&ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.PASTA.id,
                                name = RecipeCategory.PASTA.name,
                            )
                        ),
                        description = "This refreshing tuna tostadas recipe is great for a hot summer day! White albacore tuna, onion, and corn are mixed with the flavors of lime, cilantro, and piquant hot sauce. Serve on a tostada or in a taco shell!",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "3 (5 ounce) cans solid white tuna packed in water, drained",
                            "½ (10 ounce) can sweet corn, drained",
                            "½ onion, finely chopped",
                            "1 bunch cilantro, finely chopped",
                            "12 tablespoons salsa",
                            "1 lime, juiced",
                            "hot sauce to taste",
                            "salt and pepper to taste",
                            "sour cream, for topping",
                            "8 tostada shells",
                        ),
                        steps = mutableListOf(
                            "With a fork, flake tuna into a bowl. Stir in corn, onion, cilantro, salsa, and lime juice. Season with a few dashes of hot sauce, and salt and pepper to taste. Stir to combine, then spoon onto tostada shells. Top each tostada with a dollop of sour cream and a bit more hot sauce.",
                        )
                    ),
                    Recipe(
                        name = "Amatriciana",
                        featuredImageUrl = "https://images.unsplash.com/photo-1621510456681-2330135e5871?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHw2fHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw&ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.SEAFOOD.id,
                                name = RecipeCategory.SEAFOOD.name,
                            )
                        ),
                        description = "This is a classic Italian pasta dish. Use fresh basil when it's in season; at other times, use fresh flat-leaf parsley.",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "4 slices bacon, diced",
                            "½ cup chopped onion",
                            "1 teaspoon minced garlic",
                            "¼ teaspoon crushed red pepper flakes",
                            "2 (14.5 ounce) cans stewed tomatoes",
                            "1 pound linguine pasta, uncooked",
                            "1 tablespoon chopped fresh basil",
                            "2 tablespoons grated Parmesan cheese",
                        ),
                        steps = mutableListOf(
                            "Cook diced bacon in a large saucepan over medium high heat until crisp, about 5 minutes. Drain all but 2 tablespoons of drippings from the pan.",
                            "Add onions, and cook over medium heat about 3 minutes. Stir in garlic and red pepper flakes; cook 30 seconds. Add canned tomatoes, undrained; simmer 10 minutes, breaking up tomatoes.",
                            "Meanwhile, cook the pasta in a large pot of 4 quarts boiling salted water until al dente. Drain.",
                            "Stir basil into the sauce, and then toss with cooked pasta. Serve with grated Parmesan cheese.",
                        )
                    ),
                    Recipe(
                        name = "Hamburger Steak with Onions and Gravy",
                        featuredImageUrl = "https://images.unsplash.com/photo-1614961909013-1e2212a2ca87?ixid=Mnw0NDYyMzV8MHwxfHNlYXJjaHw3fHxsYXNhZ25hfGVufDB8fHx8MTY4MzY1MDI4Mw&ixlib=rb-4.0.3",
                        categories = mutableListOf(
                            RecipeType(
                                id = RecipeCategory.BREAKFAST.id,
                                name = RecipeCategory.BREAKFAST.name,
                            )
                        ),
                        description = "Tasty hamburger \"steaks\" smothered in gravy and onions. Serve with hot rice or potatoes for an easy-to-make dinner classic. It's a great way to dress up a pound of ground beef and you probably have all the ingredients on hand!",
                        prepTimeInMillis = 600000,
                        cookTimeInMillis = 1200000,
                        ingredients = mutableListOf(
                            "1 pound ground beef",
                            "¼ cup bread crumbs",
                            "1 egg",
                            "1 teaspoon Worcestershire sauce",
                            "½ teaspoon seasoned salt",
                            "1½ teaspoon onion powder",
                            "½ teaspoon garlic powder",
                            "⅛ teaspoon ground black pepper",
                            "1 tablespoon vegetable oil",
                            "1 cup thinly sliced onion",
                            "2 tablespoons all-purpose flour",
                            "1 cup beef broth",
                            "1 tablespoon cooking sherry",
                            "½ teaspoon seasoned salt",
                        ),
                        steps = mutableListOf(
                            "Mix ground beef, bread crumbs, egg, Worcestershire sauce, salt, onion powder, garlic powder, and pepper together in a large bowl until combined. Form into 8 balls and flatten into patties.",
                            "Heat oil in a large skillet over medium heat. Add patties and onion; fry until patties are nicely browned, about 4 minutes per side. Transfer beef patties to a plate, and keep warm.",
                            "Sprinkle flour over onions and drippings in the skillet. Stir in flour with a fork, scraping bits of beef off of the bottom of the skillet as you stir. Gradually mix in beef broth and sherry. Season with seasoned salt. Simmer and stir over medium-low heat until gravy thickens, about 5 minutes.",
                            "Reduce heat to low, return patties to the gravy, cover, and simmer until cooked through, about 15 minutes.",
                        )
                    ),
                )