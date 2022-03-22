package com.bestie.RestAPI.race;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaceController {

	@Autowired
	JdbcRaceRepository raceRepository;
	
	@GetMapping("/race/name")
	public Race getRaceByName(@RequestBody String name) {
		if (!name.equals("")) {
			Race race = raceRepository.getRaceByName(name).get(0);
			return race;
		}
		return null;
	}
	
	@GetMapping("/list/races")
	public List<Race> getAllRace() {
		return raceRepository.getAllRaces();
	}
	
	@GetMapping("/races/{id_specie}")
	public List<Race> getRaceBySpecie (@PathVariable int id_specie) {
		return raceRepository.getRaceBySpecie(id_specie);
	}
	
	@GetMapping("/race/{id_race}")
	public Race getRaceById (@PathVariable("id_race") int id_race) {
		Race race = raceRepository.getRaceById(id_race).get(0);
		return race;
	}
	
	@PutMapping("/updateRace/{name}/{information}/{size}/{id_race}")
	public void updateRace(@PathVariable("name") String name, @PathVariable("information") String information, @PathVariable("size") String size, @PathVariable("id_race") int id_race) {
		raceRepository.updateRace(name, information, size, id_race);
	}
	
	@DeleteMapping("/deleteRace/{id_race}")
	public void deleteRace(@PathVariable("id_race") int id_race) {
		raceRepository.deleteRace(id_race);
	}

}
