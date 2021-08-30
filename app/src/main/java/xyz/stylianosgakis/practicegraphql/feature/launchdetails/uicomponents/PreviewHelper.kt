package xyz.stylianosgakis.practicegraphql.feature.launchdetails.uicomponents

import xyz.stylianosgakis.apollo.LaunchDetailsQuery

internal fun getPreviewLaunch(
    id: Int = 1
): LaunchDetailsQuery.Launch = LaunchDetailsQuery.Launch(
    id = id.toString(),
    site = "CCAFS SLC 40",
    mission = LaunchDetailsQuery.Mission(
        name = "Starlink-15 (v1.0)",
        missionPatch = "https://images2.imgbox.com/9a/96/nLppz9HW_o.png"
    ),
    rocket = LaunchDetailsQuery.Rocket(
        id = "1",
        name = "rocket's name",
        type = "rocket's type",
    ),
    isBooked = false
)
