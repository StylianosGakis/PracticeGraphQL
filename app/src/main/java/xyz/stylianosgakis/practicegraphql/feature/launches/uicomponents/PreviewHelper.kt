package xyz.stylianosgakis.practicegraphql.feature.launches.uicomponents

import xyz.stylianosgakis.apollo.LaunchListQuery

internal fun getPreviewLaunch(
    id: Int = 1
): LaunchListQuery.Launch = LaunchListQuery.Launch(
    id = id.toString(),
    site = "CCAFS SLC 40",
    mission = LaunchListQuery.Mission(
        name = "Starlink-15 (v1.0)",
        missionPatch = "https://images2.imgbox.com/9a/96/nLppz9HW_o.png"
    )
)
