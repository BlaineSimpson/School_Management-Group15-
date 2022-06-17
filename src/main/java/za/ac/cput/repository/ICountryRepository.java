/*
CountryRepository.java
Repository interface for country
Author: Demi Farquhar (220322104)
Date: 15 June 2022
 */
package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Country;

@Repository
public interface ICountryRepository extends JpaRepository<Country,String> {

}
