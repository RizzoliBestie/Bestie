package com.bestie.RestAPI.race;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaceController {

	@Autowired
	JdbcRaceRepository raceRepository;
	
	@RequestMapping("/race/name")
	public Race getRaceByName(@RequestBody String name) {
		if (!name.equals("")) {
			Race race = raceRepository.getRaceByName(name).get(0);
			return race;
		}
		return null;
	}
	
	@RequestMapping("/list/races")
	public List<Race> getAllRace() {
		return raceRepository.getAllRaces();
	}
	
	@RequestMapping("/races/{id_specie}")
	public List<Race> getRaceBySpecie (@PathVariable int id_specie) {
		return raceRepository.getRaceBySpecie(id_specie);
	}
	
	@RequestMapping("/race/{id_race}")
	public Race getRaceById (@PathVariable int id_race) {
		Race race = raceRepository.getRaceById(id_race).get(0);
		return race;
	}

}
