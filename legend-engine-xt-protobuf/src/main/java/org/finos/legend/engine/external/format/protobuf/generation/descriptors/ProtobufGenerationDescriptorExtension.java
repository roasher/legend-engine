// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.external.format.protobuf.generation.descriptors;

import java.util.List;
import org.finos.legend.engine.external.shared.format.extension.GenerationExtension;
import org.finos.legend.engine.external.shared.format.extension.GenerationMode;
import org.finos.legend.engine.external.shared.format.generations.description.GenerationConfigurationDescription;
import org.finos.legend.engine.external.shared.format.imports.description.ImportConfigurationDescription;
import org.finos.legend.engine.language.pure.compiler.toPureGraph.CompileContext;
import org.finos.legend.engine.language.pure.modelManager.ModelManager;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.PackageableElement;
import org.finos.legend.pure.generated.Root_meta_pure_generation_metamodel_GenerationConfiguration;
import org.finos.legend.pure.generated.Root_meta_pure_generation_metamodel_GenerationOutput;

@Deprecated
public class ProtobufGenerationDescriptorExtension implements GenerationExtension
{
    @Override
    public String getLabel()
    {
        return "Protobuf Descr";
    }

    @Override
    public String getKey()
    {
        return "protobuf-descriptor";
    }


    @Override
    public GenerationMode getMode()
    {
        return GenerationMode.Schema;
    }

    @Override
    public GenerationConfigurationDescription getGenerationDescription()
    {
        return null;
    }

    @Override
    public ImportConfigurationDescription getImportDescription()
    {
        return null;
    }

    @Override
    public Object getService(ModelManager modelManager)
    {
        return new ProtobufDescriptorGenerationController(modelManager);
    }

    @Override
    public List<Root_meta_pure_generation_metamodel_GenerationOutput> generateFromElement(PackageableElement element, CompileContext compileContext)
    {
        return null;
    }

    @Override
    public Root_meta_pure_generation_metamodel_GenerationConfiguration defaultConfig(CompileContext context)
    {
        return null;
    }
}
