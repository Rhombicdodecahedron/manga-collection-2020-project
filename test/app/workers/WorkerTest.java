/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import app.beans.Manga;
import java.util.HashMap;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Alexis
 */
public class WorkerTest {

//    No				In						Out
//-----------------------------------------------------------------------------------------------------------
//    1                                 null						HashMap<String,Manga>
//    2                                 ""						HashMap<String,Manga>
//    3                                 "Ano"						HashMap<String,Manga>
//------------------------------------------------------------------------------------------------------------
    @Test
    public void testRechercheManga() {

        WrkManga wrkManga = new WrkManga();
        HashMap<String, Manga> expResult = new HashMap<>();
        //    1                  null		HashMap<String,Manga>
        assertEquals(expResult, wrkManga.rechercheMangaContenant(null));
        //    2                  ""		HashMap<String,Manga>
        assertEquals(expResult, wrkManga.rechercheMangaContenant(""));
        //    3                 "Ano"		HashMap<String,Manga>   
        assertEquals(expResult, wrkManga.rechercheMangaContenant("Ano"));

    }

}
