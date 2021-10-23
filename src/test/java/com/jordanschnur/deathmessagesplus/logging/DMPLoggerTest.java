package com.jordanschnur.deathmessagesplus.logging;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.jordanschnur.deathmessagesplus.DeathMessagesPlusMain;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DMPLoggerTest {
    private ServerMock server;
    private DeathMessagesPlusMain plugin;

    private DMPLogger target;


    @Before
    public void setUp() throws IOException {
        this.server = MockBukkit.mock();
        this.plugin = (DeathMessagesPlusMain) MockBukkit.load(DeathMessagesPlusMain.class);

        this.server.set
        this.target = new DMPLogger(this.plugin);
    }

    @Test
    public void testConstruct() throws Exception {
        assertNotNull(this.target.getContext());
    }

    @After
    public void tearDown() {
        MockBukkit.unmock();
    }
}
