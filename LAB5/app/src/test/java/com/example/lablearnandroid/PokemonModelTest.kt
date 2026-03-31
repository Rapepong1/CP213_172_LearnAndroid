package com.example.lablearnandroid

import com.example.lablearnandroid.utils.PokedexResponse
import com.example.lablearnandroid.utils.PokemonEntry
import com.example.lablearnandroid.utils.PokemonSpecies
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class PokemonModelTest {

    @Test
    fun testPokemonSpeciesCreation() {
        val species = PokemonSpecies("Pikachu", "https://pokeapi.co/api/v2/pokemon-species/25/")
        assertEquals("Pikachu", species.name)
        assertEquals("https://pokeapi.co/api/v2/pokemon-species/25/", species.url)
    }

    @Test
    fun testPokemonEntryCreation() {
        val species = PokemonSpecies("Bulbasaur", "https://pokeapi.co/api/v2/pokemon-species/1/")
        val entry = PokemonEntry(1, species)
        assertEquals(1, entry.entry_number)
        assertEquals("Bulbasaur", entry.pokemon_species.name)
    }

    @Test
    fun testPokedexResponseCreation() {
        val species = PokemonSpecies("Charmander", "https://pokeapi.co/api/v2/pokemon-species/4/")
        val entry = PokemonEntry(4, species)
        val response = PokedexResponse(listOf(entry))
        
        assertNotNull(response.pokemon_entries)
        assertEquals(1, response.pokemon_entries.size)
    }

    @Test
    fun testPokemonEntryListData() {
        val list = mutableListOf<PokemonEntry>()
        list.add(PokemonEntry(1, PokemonSpecies("A", "URL1")))
        list.add(PokemonEntry(2, PokemonSpecies("B", "URL2")))
        
        val response = PokedexResponse(list)
        assertEquals("A", response.pokemon_entries[0].pokemon_species.name)
        assertEquals("B", response.pokemon_entries[1].pokemon_species.name)
    }

    @Test
    fun testEmptyPokedexResponse() {
        val response = PokedexResponse(emptyList())
        assertEquals(0, response.pokemon_entries.size)
    }
}
